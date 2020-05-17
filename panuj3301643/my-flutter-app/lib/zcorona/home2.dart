import 'package:flutter/material.dart';
import 'package:hello_world/zcorona/district.dart';
import 'package:hello_world/zcorona/districtinfo.dart';
import 'package:gradient_bottom_navigation_bar/gradient_bottom_navigation_bar.dart';

class Home2 extends StatefulWidget {

  Map data;
  Home2({Key key, @required this.data}):super(key:key);

  @override
  _Home2State createState() => _Home2State(val:data);
}

class _Home2State extends State<Home2> {

  Map val;
  _Home2State({this.val});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      extendBody: true,
      body: PageView(
        children: <Widget>[       
          District(val: val),
          Districtinfo()
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
                color: Colors.white70,
              ), 
              title: Text(
                "STATES",
                style: TextStyle(
                  color: Colors.white
                ),
              )
            ),       
            BottomNavigationBarItem(
              icon: Icon(
                Icons.place,
                color: Colors.white70,
              ), 
              title: Text(
                "DISTRICT",
                style: TextStyle(
                  color: Colors.white
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
        tooltip: "Important Notes", 
        child: Icon(
          Icons.add, 
          color: Colors.black
        ),
        onPressed: () {},
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }

  int selecteditem=0;
  var pagecontroller = PageController();

}





