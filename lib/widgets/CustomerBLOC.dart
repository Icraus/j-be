import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


class Customer {
  Customer();
  String name = "";
  int id = 0;
  String phone = "";
  String countryCode = "";
  bool isValid = false;
  String country = "";

  Customer.fromJson(Map<String, dynamic> json)
      : name = json['name'],
        phone = json['phone'],
        country = json['country'],
        countryCode = json['countryCode'],
        id = json['id'],
        isValid = json['valid'];

  Map<String, dynamic> toJson() => {
    'id': id,
    'name': name,
    'phone': phone,
    'country': country,
    'countryCode': countryCode,
    'valid': isValid,
  };
}

class CustomerBLoC{
  Future<List<Customer>> fetchAllCustomers(int start, int count) async {
    String param = "start=$start";
    param += "&count=$count";
    final response = await http.get(Uri.parse('http://10.0.2.2:8080/rest/customers?$param'));
    if (response.statusCode == 200) {
      var l = jsonDecode(response.body)["items"];
      var x = l.map((model)=> Customer.fromJson(model));
      List<Customer> customers = List<Customer>.from(x);
      return customers;
    } else {
      throw Exception('Failed to load customers');
    }
  }
}

class CustomerDataSource extends DataTableSource {
  // Generate some made-up data
  List<Customer> _customerDataList = List<Customer>.empty(growable: true);
  set customerDataList(List<Customer> tmp){
    _customerDataList = tmp;
  }
  bool get isRowCountApproximate => false;
  int get rowCount => _customerDataList.length;
  int get selectedRowCount => 0;
  DataRow getRow(int index) {
    return DataRow(cells: [
      DataCell(Text(_customerDataList[index].id.toString())),
      DataCell(Text(_customerDataList[index].name)),
      DataCell(Text(_customerDataList[index].phone)),
      DataCell(Text(_customerDataList[index].countryCode)),
      DataCell(Text(_customerDataList[index].country)),
      DataCell(Checkbox(value: _customerDataList[index].isValid, onChanged: null,)),
      DataCell(Container(
          width: 30,
          height: 30,
          child: Center(
              child: ClipRRect(
                borderRadius: BorderRadius.circular(100.0),
                child: Image(image: getCountryImage(_customerDataList[index].country).image),
                ),
              )
          )
      ),
    ]);
  }
  Image getCountryImage(String country) {
    switch(country){
      case "Morocco":
        return Image.asset('icons/flags/png/ma.png', package: 'country_icons');
      case "Cameroon":
        return Image.asset('icons/flags/png/cm.png', package: 'country_icons');
      case "Ethiopia":
        return Image.asset('icons/flags/png/et.png', package: 'country_icons');
      case "Mozambique":
        return Image.asset('icons/flags/png/mz.png', package: 'country_icons');
      case "Uganda":
        return Image.asset('icons/flags/png/ug.png', package: 'country_icons');
      default:
        return Image.asset('icons/flags/png/ug.png', package: 'country_icons');
    }
  }
}