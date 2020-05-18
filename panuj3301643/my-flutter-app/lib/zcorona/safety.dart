import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';
import 'package:hello_world/zcorona/FadeAnimation.dart';

class Safety extends StatefulWidget {
  @override
  _SafetyState createState() => _SafetyState();
}

class _SafetyState extends State<Safety> {

  _launchURL1() async {

  const url = "https://www.mohfw.gov.in/pdf/coronvavirushelplinenumber.pdf";

  if (await canLaunch(url)) {

    await launch(url);

  } else {

    throw 'Could not launch $url';

    }

  }

  
  _launchURL2() async {

  const url = "https://www.mohfw.gov.in/";

  if (await canLaunch(url)) {

    await launch(url);

  } else {

    throw 'Could not launch $url';

    }

  }

    _launchURL3() async {

  const url = "https://www.who.int/emergencies/diseases/novel-coronavirus-2019";

  if (await canLaunch(url)) {

    await launch(url);

  } else {

    throw 'Could not launch $url';

    }

  }

  
    _launchURL4() async {

  const url = "https://www.cdc.gov/coronavirus/2019-ncov/faq.html";

  if (await canLaunch(url)) {

    await launch(url);

  } else {

    throw 'Could not launch $url';

    }

  }

      _launchURL5() async {

  const url = "https://coronavirus.thebaselab.com/";

  if (await canLaunch(url)) {

    await launch(url);

  } else {

    throw 'Could not launch $url';

    }

  }



  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child:Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            Image.asset("assets/Images/5.png"),
            Container(
              child: Row(
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
                           "Hepline Numbers\n     [BY STATE]",
                           style: TextStyle(
                             fontSize: 15.0,
                             color: Colors.green,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                         RaisedButton(
                           shape: StadiumBorder(),
                           elevation: 0.0,
                           highlightColor: Colors.green[200],
                           onPressed: () {
                             _launchURL1();
                            },
                           color: Colors.white,
                           child: Text("Click"),
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
                              "Ministry Of Health And \n       Family Welfare\n        Gov. Of INDIA",
                              style: TextStyle(
                                fontSize: 15.0,
                                color: Colors.blue,
                                fontWeight: FontWeight.bold
                              ),
                            ),
                            RaisedButton(
                              shape: StadiumBorder(),
                              elevation: 0.0,
                              highlightColor: Colors.blue[200],
                              onPressed: () {
                                _launchURL2();
                              },
                              color: Colors.white,
                              child: Text("Click"),
                            ),
                          ],
                        )
                      )
                    ),
                  ),
                ],
              ),
            ),
            Container(
              child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
               FadeAnimation(0.6,
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
                           " WHO : COVID-19\n    Home Page",
                           style: TextStyle(
                             fontSize: 15.0,
                             color: Colors.red,
                             fontWeight: FontWeight.bold
                           ),
                         ),
                         RaisedButton(
                           shape: StadiumBorder(),
                           elevation: 0.0,
                           highlightColor: Colors.red[200],
                           onPressed: () {
                             _launchURL3();
                            },
                           color: Colors.white,                         
                           child: Text("Click"),
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
                                "CDC",
                                style: TextStyle(
                                  fontSize: 15.0,
                                  color: Colors.amber,
                                  fontWeight: FontWeight.bold
                                ),
                              ),
                              RaisedButton(
                                shape: StadiumBorder(),
                                elevation: 0.0,
                                highlightColor: Colors.amber[200],
                                onPressed: () {
                                  _launchURL4();
                                },
                                color: Colors.white,
                                child: Text("Click"),
                              ),
                            ],
                          )
                        )
                      ),
                    ),
                  ],
                ),
              ),
              Container(
                child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  FadeAnimation(1.0,
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
                              " COVID-19 Global\n        Tracker",
                              style: TextStyle(
                                fontSize: 15.0,
                                color: Colors.purple,
                                fontWeight: FontWeight.bold
                              ),
                            ),
                            RaisedButton(
                              shape: StadiumBorder(),
                              elevation: 0.0,
                              highlightColor: Colors.purple[200],
                              onPressed: () {
                                _launchURL5();
                                },
                              color: Colors.white,
                              child: Text("Click"),
                            ),
                          ],
                        )
                      )
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

