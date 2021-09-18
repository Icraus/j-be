import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:j_customer_mobile_clinet/widgets/CustomerWidget.dart';
import 'package:json_table/json_table.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CustomerPage(title: 'Flutter Demo Home Page'),
    );
  }
}

