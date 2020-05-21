
//cloud functions

//resizing
const functions = require('firebase-functions')
const { Storage } = require('@google-cloud/storage');
const projectId = 'gcpproject-271606';
let gcs = new Storage ({
  projectId
});
const os = require('os');
const path = require('path');
const spawn = require("child-process-promise").spawn;

//http,realtime database changes
const cors = require("cors")({ origin: true });
const Busboy = require("busboy");
const fs = require("fs");

//for pubsub(cloud_function)
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);



exports.onFileChange = functions.storage.object().onFinalize((event,context) => {
  console.log(event);
  //const object = event.data;
  const bucket = event.bucket;
  const contentType = event.contentType;
  const filePath = event.name;
  console.log("File change detected, function execution started");

  /*if (object.resourceState === "not_exists") {
    console.log("We deleted a file, exit...");
    return;
  }*/

  if (path.basename(filePath).startsWith("resized-")) {
    console.log("We already renamed that file!");
    return;
  }

  const destBucket = gcs.bucket(bucket);
  const tmpFilePath = path.join(os.tmpdir(), path.basename(filePath));
  const metadata = { contentType: contentType };
  return destBucket
    .file(filePath)
    .download({
      destination: tmpFilePath
    })
    .then(() => {
      return spawn("convert", [tmpFilePath, "-resize", "500x500", tmpFilePath]);
    })
    .then(() => {
      return destBucket.upload(tmpFilePath, {
        destination: "resized-" + path.basename(filePath),
        metadata: metadata
      });
    });

});



exports.onDataAdded = functions.database.ref('/message/{id}').onCreate((event,context) => {
  console.log('event: ' + event)
  const data = event.val();
  const newData = {
    msg: data.msg.toUpperCase()//event.params.id + '-' + data.msg.toUpperCase()
  };
  return event.ref.child('copiedData').set(newData) //return event.ref.parent.child('copiedData').set(newData)
});













exports.sendNotificationToTopic = functions.firestore.document('test/{uid}').onWrite(async (event) => {
    //let docID = event.after.id;
    let title = event.after.get('title');
    let content = event.after.get('content');
    var message = {
        notification: {
            title: title,
            body: content,
        },
        topic: 'testtopic',
    };

    let response = await admin.messaging().send(message);
    console.log(response);
});

exports.sendNotificationToFCMToken = functions.firestore.document('messages/{mUid}').onWrite(async (event) => {
    const uid = event.after.get('userUid');
    const title = event.after.get('title');
    const content = event.after.get('content');
    let userDoc = await admin.firestore().doc(`users/${uid}`).get();
    let fcmToken = userDoc.get('fcm');

    var message = {
        notification: {
            title: title,
            body: content,
        },
        token: fcmToken,
    }

    let response = await admin.messaging().send(message);
    console.log(response);
});



exports.sendNotificationToTopic2 = functions.firestore.document('hand/{userid}').onWrite(async (event) => {//(or) onUpdate
    //let docID = event.after.id;
    let touch = event.after.get('touch');
    let detect = event.after.get('detect');

    var message = {
        notification: {
            title: detect,
            body: 'Please wash your hands',
        },
        topic: 'testingtopic',
    };
    if(touch === '1' && detect === '1'){
      let response = await admin.messaging().send(message);
    console.log(response);  
    }

    
});

exports.touch = functions.firestore.document('hand/{userid}').onWrite(async (event) => {//(or) onUpdate
    //let docID = event.after.id;
    let touch = event.after.get('touch');

    var message = {
        notification: {
            title: 'Touch sensed(Motion detected)',
            body: 'Objects::Chair:{coordinates:0.2,0.4,0.8,0.1 , _id:12 , Time_stamp:=07:05pm } more details....',
        },
        topic: 'testingtopic',
    };
    if(touch === '1'){
      let response = await admin.messaging().send(message);
    console.log(response);  
    }

    
});

exports.wash = functions.firestore.document('hand/{userid}').onWrite(async (event) => {//(or) onUpdate
    //let docID = event.after.id;
    let wash = event.after.get('wash');

    var message = {
        notification: {
            title: 'hand washed',
            body: 'Details of touch history erased, Time_stamp:=07:10pm,pose & skeleton coordinates{coordinates:222,122,20,30;coordinates:170,120,100,200 } more details....',
        },
        topic: 'testingtopic',
    };
    if(wash === '1'){
      let response = await admin.messaging().send(message);
    console.log(response);  
    }

    
});

exports.detect = functions.firestore.document('hand/{userid}').onWrite(async (event) => {//(or) onUpdate
    //let docID = event.after.id;
    
    let detect = event.after.get('detect');

    var message = {
        notification: {
            title: 'On Laptop(Motion detected)',
            body: 'Face:{coordinates:0.12,0.10,0.18,0.22 , _id:12 , Time_stamp:=07:15pm },hand:{coordinates:0.17,0.12,0.10,0.20 } more details....',
        },
        topic: 'testingtopic',
    };
    if(detect === '1'){
      let response = await admin.messaging().send(message);
    console.log(response);  
    }

    
});



