import 'dart:async';

import 'package:flutter/services.dart';

class FlutterSmartposPrinter {
  static const MethodChannel _channel =
      const MethodChannel('flutter_smartpos_printer');

  /// Initialize printer
  Future<bool?> initPrinter() async {
    return await _channel.invokeMethod('initPrinter');
  }

  /// Set printer parameter
  Future<void> releasePrinter() async {
    await _channel.invokeMethod('releasePrinter');
  }

  /// Printer status
  Future<int?> getStatus() async {
    return await _channel.invokeMethod('getStatus');
  }

  /// Set printer parameter
  Future<int?> setPrintParameters({
    required int fontSize,
    int align = 1,
    int fontWeight = 1,
  }) async {
    return await _channel.invokeMethod('setPrintParameters', <String, dynamic>{
      "fontSize": fontSize,
      "align": align,
      "fontWeight": fontWeight,
    });
  }

  /// Set line spacing
  Future<bool?> setYSpace(int space) async {
    return await _channel.invokeMethod('setYSpace', <String, dynamic>{
      "space": space.toString(),
    });
  }

  /// Feed paper
  Future<int?> feedPaper(int space) async {
    return await _channel.invokeMethod('feedPaper', <String, dynamic>{
      "space": space.toString(),
    });
  }

  /// Start print
  Future<void> startPrint() async {
    await _channel.invokeMethod('startPrint');
  }

  /// Print text single line
  Future<int?> printText(String text) async {
    return await _channel.invokeMethod('printText', <String, dynamic>{
      "text": text,
    });
  }

  /// Print QR Code
  Future<int?> printQrCode(String code) async {
    return await _channel.invokeMethod('printQrCode', <String, dynamic>{
      "code": code,
    });
  }

  /// Print Bitmap image
  Future<int?> printBitmap(String filename) async {
    return await _channel.invokeMethod('printBitmap', <String, dynamic>{
      "filename": filename,
    });
  }

  /// Append Text
  Future<void> appendText(String text) async {
    await _channel.invokeMethod('appendText', <String, dynamic>{
      "text": text,
    });
  }

  /// Append Bitmap image
  Future<void> appendBitmap(String text) async {
    await _channel.invokeMethod('appendBitmap', <String, dynamic>{
      "text": text,
    });
  }

  /// Append QR Code data
  Future<void> appendQrCode(String text) async {
    await _channel.invokeMethod('appendQrCode', <String, dynamic>{
      "text": text,
    });
  }

  /// Append script
  Future<void> appendScript(String text) async {
    await _channel.invokeMethod('appendScript', <String, dynamic>{
      "text": text,
    });
  }

  /// Imprimer le reçu de Simbo
  Future<int?> printSimboReceipt({
    required int montant,
    required String reference,
    required String date,
    required String libelle,
    required String ville,
    required String tel,
    required String ctb,
    required String ref,
    required String fax,
    required String contribuable,
    required String matricule,
    required String equipement,
    required String agent,
    String? region,
  }) async {
    return await _channel.invokeMethod('printSimboReceipt', <String, dynamic>{
      "montant": montant.toString(),
      "reference": reference,
      "date": date,
      "libelle": libelle,
      "ville": ville,
      "tel": tel,
      "ctb": ctb,
      "ref": ref,
      "fax": fax,
      "contribuable": contribuable,
      "matricule": matricule,
      "equipement": equipement,
      "agent": agent,
      "region": region,
    });
  }
}
