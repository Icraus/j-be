
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:json_table/json_table.dart';

class CustomerPage extends StatefulWidget {
  CustomerPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _CustomerPageState createState() => _CustomerPageState();
}

class _CustomerPageState extends State<CustomerPage> {
  var json = jsonDecode('[{"name":"ahmed", "id": 5 }, {"name":"ahmeds", "id": 6 }, {"name":"ahmeda", "id": 55 }]');
  var columns = [
    JsonTableColumn("id", label: "ID"),
    JsonTableColumn("name", label: "Name"),
    JsonTableColumn("phone", label: "Phone"),
    JsonTableColumn("country", label: "Country"),
    JsonTableColumn("countryCode", label: "Country Code"),
    JsonTableColumn("valid", label: "Is Valid"),
  ];
  @override
  void initState() {
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: JsonTable(
        json,
        columns: columns,
        showColumnToggle: true,
      ),
    );
  }
}