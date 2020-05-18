import 'package:flutter/material.dart';
import 'package:hello_world/zcorona/india.dart';
import 'package:hello_world/zcorona/safety.dart';
import 'package:hello_world/zcorona/states.dart';
import 'package:gradient_bottom_navigation_bar/gradient_bottom_navigation_bar.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {

  Future<bool> backpressed() {
    return showDialog(
      context: context,
      builder: (context)=> AlertDialog(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(20.0)
        ),
        title: Text(
          "Do you really want to exit?",
          style: TextStyle(
            fontSize: 15.0
          ),
        ),
        actions: <Widget>[
          MaterialButton(
            shape: StadiumBorder(),
            elevation: 1.0,
            color: Colors.white,
            child: Text(
              "Yes",
              style: TextStyle(
                color: Colors.blue
              ),
            ),
            splashColor: Colors.blue[100],
            onPressed: () {
              Navigator.pop(context,true);
            },
          ),
          MaterialButton(
            shape: StadiumBorder(),
            elevation: 3.0,
            color: Colors.white,
            child: Text(
              "No",
              style: TextStyle(
                color: Colors.blue
              ),
            ),
            splashColor: Colors.blue[100],
            onPressed: () {
              Navigator.pop(context,false);  //if its true then it will exit
            },
          ),
        ],
      )
    );
  }


  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () {
        return backpressed();
      },
      child: Scaffold(
        backgroundColor: Colors.white,
        body: PageView(
          children: <Widget>[       
            India(),
            Statesofindia()
          ],
          onPageChanged: (index) {
            setState(() {
              selecteditem = index;
            });
          },
          controller: pagecontroller,
        ),
        bottomNavigationBar: BottomAppBar(
          clipBehavior: Clip.antiAlias,
          notchMargin: 3.5,
          shape: CircularNotchedRectangle(),
          child: GradientBottomNavigationBar(
            backgroundColorStart: Colors.white,
            backgroundColorEnd: Colors.white,
            items: <BottomNavigationBarItem>[        //Must Fill both the icon and title down here
              BottomNavigationBarItem(
                icon: Icon(
                  Icons.home,
                  color: Colors.black,
                ), 
                title: Text(
                  "INDIA",
                  style: TextStyle(
                    color: Colors.black
                  ),
                )
              ),       
              BottomNavigationBarItem(
                icon: Icon(
                  Icons.place,
                  color: Colors.black,
                ), 
                title: Text(
                  "STATES",
                  style: TextStyle(
                    color: Colors.black
                  ),
                )
              ),
            ],
            currentIndex: selecteditem,
            onTap: (index) {
              setState(() {
                selecteditem = index;
                pagecontroller.animateToPage(
                  selecteditem, 
                  duration: Duration(milliseconds: 200), 
                  curve: Curves.linear
                );
              });
            },
          ),
        ),
        floatingActionButton: FloatingActionButton(
          backgroundColor: Colors.white,
          tooltip: "Help", 
          child: Icon(
            Icons.help,
            color: Colors.black
          ),
          onPressed: () {
            Navigator.push(context, MaterialPageRoute(builder: (context)=> Safety()));
          },
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      )
    );
  }

  int selecteditem=0;
  var pagecontroller = PageController();

}





