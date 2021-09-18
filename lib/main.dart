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
      title: 'Jumia Test',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: CustomerPage(title: 'Jumia Test'),
    );
  }
}

