import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:hello_world/zcorona/FadeAnimation.dart';
import 'package:hello_world/zcorona/district.dart';
import 'package:hello_world/zcorona/home2.dart';
import 'package:http/http.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';

class Statesofindia extends StatefulWidget {
  @override
  _StatesofindiaState createState() => _StatesofindiaState();
}

class _StatesofindiaState extends State<Statesofindia> {

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
      itemCount: data==null?0:data.length-1,
      itemBuilder: (BuildContext context, int index) {
        if (data==null){
          return loading();
        }else{
          return statesPage(index);
        }
      },
    );
  }

  Widget statesPage(index) {
    return FadeAnimation(0.0,
      Container(
        child: Card(
          child: ListTile(
            onTap: () {
              Navigator.push(context, MaterialPageRoute(builder: (context)=> District(val:data[index+1])));
            },
            title: Text(
              (data[index+1]["state"]),
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 13.0
              ),
            ),
            subtitle: Text(
              ("Last Time Updated: ${data[index+1]["lastupdatedtime"]}")
            ),
          ),
        ),
      ),
    );
  }

  Widget loading() {
    return Container(
      height: double.infinity,
      width: double.infinity,
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
