
import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:json_table/json_table.dart';

import 'CustomerBLOC.dart';

class CustomerPage extends StatefulWidget {
  CustomerPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _CustomerPageState createState() => _CustomerPageState();
}

class _CustomerPageState extends State<CustomerPage> {
  CustomerDataSource _data = CustomerDataSource();

  late Future<List<Customer>> customerFuture;

  @override
  void initState() {
    super.initState();
    customerFuture = CustomerBLoC().fetchAllCustomers(0, -1);
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        actions: [
          IconButton(onPressed: (){
            _data = CustomerDataSource();
            customerFuture = CustomerBLoC().fetchAllCustomers(0, -1);
            setState((){
            });
          }, icon: Icon(Icons.refresh)),
        ],
      ),
      body: Center(
        child: FutureBuilder<List<Customer>>(
            future: customerFuture,
            builder: (ctx, snapshot){
              if(snapshot.hasData){
                _data.customerDataList = snapshot.data!;
                return PaginatedDataTable(
                  source: _data,
                  header: Text('Customers'),
                  columns: [
                    DataColumn(label: Text('ID')),
                    DataColumn(label: Text('Name')),
                    DataColumn(label: Text('Phone')),
                    DataColumn(label: Text('Country Code')),
                    DataColumn(label: Text('Country')),
                    DataColumn(label: Text('Is Valid')),
                    DataColumn(label: Text('Flag')),
                  ],
                  columnSpacing: 50,
                  horizontalMargin: 10,
                  rowsPerPage: 4,
                  showFirstLastButtons: true,
                  showCheckboxColumn: false);

              }
              return const CircularProgressIndicator();
            }
        ),
      )
    );
  }

}
