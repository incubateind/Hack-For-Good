import 'package:flutter/material.dart';
import 'package:hello_world/zcorona/FadeAnimation.dart';
import 'dart:async';
import 'package:hello_world/zcorona/home.dart';
import 'package:introduction_screen/introduction_screen.dart';
import 'package:shared_preferences/shared_preferences.dart';

class Myapp4 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      routes:{
        "/home":(context)=>Home(),
      },
      title: "COVID-19 INDIA",
      debugShowCheckedModeBanner: false,
      home: SplashScreen()
    );
  }
}

class SplashScreen extends StatefulWidget {
  @override
  _SplashScreenState createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {

  int counter = 0;

  // Async func to handle Futures easier; or use Future.then
  got() async { 
    SharedPreferences prefs = await SharedPreferences.getInstance();  
    setState(() {
      counter = prefs.getInt('isfirst') ?? 0;
    });
  }
  

  @override
  void initState() {
    super.initState();
    got();
    Timer(Duration(seconds: 2), () {
      Navigator.pushReplacement(context, MaterialPageRoute(
        builder: (context) {
          if(counter == 0)
            return IntroductionPage();
          else
            return Home();
        } 
      ));
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: FadeAnimation(0.0,
        Container(
          alignment: Alignment.center,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Image.asset("assets/Images/33.png"),
              Text(
                "COVID-19\nINDIA",
                style: TextStyle(
                  fontSize: 60.0,
                  fontWeight: FontWeight.bold,
                  color: Colors.orange
                ),
              )
            ],
          )
        ),
      ),
    );
  }
}

class IntroductionPage extends StatefulWidget {
  @override
  _IntroductionPageState createState() => _IntroductionPageState();
}

class _IntroductionPageState extends State<IntroductionPage> {

  int counter = 0;

  void nextpage(context) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    // set value
     prefs.setInt('isfirst', 1);
    Navigator.pushReplacement(context, MaterialPageRoute(builder: (context)=> Home()));
  }

  List<PageViewModel> getPages() {
    return [
      PageViewModel(
        image: Image.asset("assets/Images/76.png"),
        title: "Avoid touching eyes, nose and mouth",
        body: "Wash your hands always with soap and water, Thus keeping the virus away.",
        footer: Text(
          "Why? Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, nose or mouth. From there, the virus can enter your body and can make you sick.",
          style: TextStyle(color: Colors.black),
        ),
      ),
      PageViewModel(
        image: Image.asset("assets/Images/77.png"),
        title: "Social Distancing",
        body: "Maintain at least 1 metre (3 feet) distance between yourself and anyone who is coughing or sneezing.",
        footer: Text(
          "Why? When someone coughs or sneezes they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, including the COVID-19 virus if the person coughing has the disease.",
          style: TextStyle(color: Colors.black),
        ),
      ),
      PageViewModel(
        image: Image.asset("assets/Images/78.png"),
        title: "Stay at home",
        body: "Stay at home if you begin to feel unwell, even with mild symptoms such as headache and slight runny nose, until you recover.",
        footer: Text(
          "Why? Avoiding contact with others and visits to medical facilities will allow these facilities to operate more effectively and help protect you and others from possible COVID-19 and other viruses.",
          style: TextStyle(color: Colors.black),
        ),
      ),
      PageViewModel(
        image: Image.asset("assets/Images/79.png"),
        title: "Increase immunity",
        body: "Increase immunity by doing Yoga, Eat more fermented foods or take a probiotic supplement, Get enough sleep etc..",
        footer: Text(
          "Why? People can help their immune systems by washing their hands regularly to prevent infections, eating nutritious foods, getting plenty of exercise, getting enough sleep, and getting regular medical checkups.",
          style: TextStyle(color: Colors.black),
        ),
      ),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: IntroductionScreen(
          globalBackgroundColor: Colors.white,
          pages: getPages(),
          done: Text(
            "Done", 
            style: TextStyle(color: Colors.blue),
          ),
          onDone: () {
            return nextpage(context);
          },
          showSkipButton: true,
          skip: Text(
            "Skip",
            style: TextStyle(
              color: Colors.blue
            ),
          ),
          onSkip: () {
            return nextpage(context);
          },
        ),
      ),
    );
  }
}