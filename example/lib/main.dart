import 'package:flutter/material.dart';
import 'package:flutter_smartpos_printer/flutter_smartpos_printer.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  FlutterSmartposPrinter _printer = FlutterSmartposPrinter();

  @override
  void initState() {
    super.initState();
    initPrinter();
  }

  @override
  void dispose() {
    _printer.releasePrinter();
    super.dispose();
  }

  Future<void> initPrinter() async {
    await _printer.initPrinter();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Simbo Printer Test'),
        ),
        body: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            ElevatedButton(
              child: Text("Print Text"),
              onPressed: () async {
                int? res = await _printer.printText("Hello Lambirou !");
                print("printText: " + res.toString());
              },
            ),
            ElevatedButton(
              child: Text("Print Image"),
              onPressed: () async {
                int? res = await _printer.printBitmap("simbo_logo_2.png");
                print("printBitmap: " + res.toString());
              },
            ),
            ElevatedButton(
              child: Text("Print QR Code"),
              onPressed: () async {
                int? res = await _printer.printQrCode("Simbo taxe");
                print("printQrCode: " + res.toString());
              },
            ),
            ElevatedButton(
              child: Text("Feed Paper"),
              onPressed: () async {
                int? res = await _printer.feedPaper(5000);
                print("feedPaper: " + res.toString());
              },
            ),
            ElevatedButton(
              child: Text("Set YSpace"),
              onPressed: () async {
                bool? res = await _printer.setYSpace(50);
                print("setYSpace: " + res.toString());
              },
            ),
            ElevatedButton(
              child: Text("Imprimer un reçu"),
              onPressed: () async {
                await _printer.printBitmap("simbo_logo_2.png");
                await _printer.printSimboReceipt(
                  montant: 1250,
                  reference: "R0010000000047",
                  date: "08/09/2021 12:43:39",
                  libelle: "Paiement taxe antérieure",
                  ville: "Mairie de la Commune III",
                  region: "District de Bamako",
                  tel: "45556890",
                  ctb: "000000000",
                  ref: "CIII/68/MALI",
                  fax: "0",
                  contribuable: "Canis Asseke",
                  matricule: "C001000006",
                  equipement: "MAG-00000005-KK",
                  agent: "Touré Toutouya",
                );
              },
            ),
          ],
        ),
      ),
    );
  }
}
