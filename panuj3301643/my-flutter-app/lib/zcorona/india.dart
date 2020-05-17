import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'dart:convert';
import 'package:hello_world/zcorona/FadeAnimation.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';

class India extends StatefulWidget {
  @override
  _IndiaState createState() => _IndiaState();
}

class _IndiaState extends State<India> {

  List data;

  void getinfo() async{
    Response response = await get("https://api.covid19india.org/data.json");
    this.setState(() { 
      var corona = jsonDecode(response.body);
      data = corona["statewise"]; 
    });
  
  }

  @override
  void initState() {
    super.initState();
    this.getinfo();
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: data==null?0:1,
      itemBuilder: (BuildContext context, int index) {
        if(data==null){
          return loading();
        }else{
          return indiaPage(index);
        }
      },
    );
  }

  Widget indiaPage(index) {
    return SingleChildScrollView(
      child: Container(
       child: Column(
         mainAxisAlignment: MainAxisAlignment.spaceEvenly,
         children: <Widget>[
           Container(
             child: Stack(
               overflow: Overflow.visible,
               children: <Widget>[
                 FadeAnimation(
                   0.0,
                   Container(
                     child: SizedBox(
                         child: Image.asset(
                           "assets/Images/1.png",
                           fit: BoxFit.cover,
                         ),
                       ),
                    ),
                 ),
                 Positioned(
                   top: 270.0,
                   right: 20.0,
                   child: FadeAnimation(0.1,
                     Container(
                       child: Column(
                         children: <Widget>[
                           Text(
                             "Last Updated",
                             style: TextStyle(
                               fontWeight: FontWeight.bold,
                               color: Colors.white
                             ),
                           ),
                           Text(
                             data[index]["lastupdatedtime"].toString(),
                             style: TextStyle(
                               color: Colors.white
                             ),
                            ),
                         ],
                       ),
                     ),
                   ),
                 ),
                 Positioned(
                   top: 340.0,
                   right: 15.0,
                   child: FadeAnimation(0.2,
                     Container(
                       child: RaisedButton.icon(
                         onPressed: () {},
                         shape: RoundedRectangleBorder(
                           borderRadius: BorderRadius.circular(20)
                         ),
                         icon: Icon(Icons.refresh),
                         label: Text("Refresh"),
                         color: Colors.white,
                         highlightColor: Colors.blue[200],
                       ),
                     ),
                   ),
                 ),
                 FadeAnimation(0.3,
                   Container(
                     height: 360.0,
                     width: 400.0,
                     alignment: Alignment.center,
                     child: Text(
                       "INDIA",
                       style: TextStyle(
                         fontSize: 60.0,
                         fontWeight: FontWeight.bold,
                         color: Colors.white
                       ),
                     ),
                   ),
                 ),
               ],
             ),
           ),
           Row(
             mainAxisAlignment: MainAxisAlignment.spaceEvenly,
             children: <Widget>[
               FadeAnimation(0.2,
                 Container(
                   height: 180.0,
                   width: 180.0,
                   child: Card(
                     shape: RoundedRectangleBorder(
                       borderRadius: BorderRadius.circular(10.0)
                     ),
                     elevation: 3.0,
                     child: Column(
                       mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                       children: <Widget>[
                         Text(
                           data[index]["confirmed"].toString(),
                           style: TextStyle(
                             fontSize: 28.0,
                             color: Colors.red,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                         Text(
                           "Confirmed Cases",
                           style: TextStyle(
                             fontSize: 14.0,
                             color: Colors.black,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                       ],
                     )
                   )
                 ),
               ),
               FadeAnimation(0.4,
                 Container(
                   height: 180.0,
                   width: 180.0,
                   child: Card(
                     shape: RoundedRectangleBorder(
                       borderRadius: BorderRadius.circular(10.0)
                     ),
                    elevation: 3.0,
                     
                     child: Column(
                       mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                       children: <Widget>[
                         Text(
                           data[index]["active"].toString(),
                           style: TextStyle(
                             color: Colors.blue,
                             fontSize: 28.0,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                         Text(
                           "Active Cases",
                           style: TextStyle(
                             color: Colors.black,
                             fontSize: 14.0,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                       ],
                     )
                   )
                 ),
               ),
             ],
           ),
           Row(
             mainAxisAlignment: MainAxisAlignment.spaceEvenly,
             children: <Widget>[
               FadeAnimation(0.6,
                 Container(
                   height: 180.0,
                   width: 180.0, 
                   child: Card(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(10.0),
                    ),
                     elevation: 3.0,
                     
                     child: Column(
                       mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                       children: <Widget>[
                         Text(
                           data[index]["recovered"].toString(),
                           style: TextStyle(
                             color: Colors.green,
                             fontSize: 28.0,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                         Text(
                           "Recovered Cases",
                           style: TextStyle(
                             color: Colors.black,
                             fontSize: 14.0,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                       ],
                     )
                   )
                 ),
               ),
               FadeAnimation(0.8,
                 Container(
                   height: 180.0,
                   width: 180.0,
                   child: Card(
                     shape: RoundedRectangleBorder(
                       borderRadius: BorderRadius.circular(10.0)
                     ),
                     elevation: 3.0,
                     
                     child: Column(
                       mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                       children: <Widget>[
                         Text(
                           data[index]["deaths"].toString(),
                           style: TextStyle(
                             color: Colors.grey,
                             fontSize: 28.0,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                         Text(
                           "Deaths Cases",
                           style: TextStyle(
                             color: Colors.black,
                             fontSize: 14.0,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                       ],
                     )
                   )
                 ),
               ),
             ],
           ),
         ],
       ),
      ),
    );
  }

  
  Widget loading() {
    print("asd");
    return Container(
      color: Colors.blue[800],
      child: Center(
        child: SpinKitChasingDots(
          color: Colors.red,
          size: 50.0,
        ),
      ),
    );
  }

}




       
        