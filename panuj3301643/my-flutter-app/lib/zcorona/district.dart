import 'package:flutter/material.dart';
import 'package:hello_world/zcorona/FadeAnimation.dart';

class District extends StatefulWidget {

  Map val;
  
  District({Key key, @required this.val}):super(key:key);

  @override
  _DistrictState createState() => _DistrictState(val:val);
}

class _DistrictState extends State<District> {
  
  Map val;
  _DistrictState({this.val});
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Container(
          color: Colors.white,
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
                   FadeAnimation(0.1,
                     Container(
                       height: 360.0,
                       width: 400.0,
                       alignment: Alignment.center,
                       child: Text(
                         val["state"],
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
                             val["confirmed"].toString(),
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
                             val["active"].toString(),
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
                             val["recovered"].toString(),
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
                             val["deaths"].toString(),
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
      ),
    );
  }
}