package com.codivoire.flutter_smartpos_printer;

import java.io.IOException;
import java.util.HashMap;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

public class FlutterSmartposPrinterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding binding) {
    channel = new MethodChannel(binding.getBinaryMessenger(), "flutter_smartpos_printer");
    channel.setMethodCallHandler(this);
    Printer.setContext(binding.getApplicationContext());
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    Printer.setActivity(binding.getActivity());
  }

  @Override
  public void onDetachedFromActivity() {
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch (call.method) {
      case "initPrinter": {
        boolean res = Printer.initPrinter();
        result.success(res);
        break;
      }
      case "releasePrinter": {
        Printer.releasePrinter();
        break;
      }
      case "startPrint": {
        Printer.startPrint();
        break;
      }
      case "setPrintParameters": {
        final String fontSize = call.argument("fontSize");
        final String alignment = call.argument("align");
        final String fontWeight = call.argument("fontWeight");

        final int size = fontSize != null ? Integer.parseInt(fontSize) : 1;
        final int align = alignment != null ? Integer.parseInt(alignment) : 1;
        final int weight = fontWeight != null ? Integer.parseInt(fontWeight) : 1;

        boolean res = Printer.setPrintParameters(size, align, weight);
        result.success(res);
        break;
      }
      case "getStatus": {
        int res = Printer.getStatus();
        result.success(res);
        break;
      }
      case "setYSpace": {
        String text = call.argument("space");
        assert text != null;
        int space = Integer.parseInt(text);
        boolean res = Printer.setYSpace(space);

        result.success(res);
        break;
      }
      case "feedPaper": {
        String space = call.argument("space");
        assert space != null;
        int feed = Integer.parseInt(space);
        int res = Printer.feedPaper(feed);

        result.success(res);
        break;
      }
      case "printText": {
        final String text = call.argument("text");
        int res = Printer.printText(text);
        result.success(res);
        break;
      }
      case "printQrCode": {
        final String code = call.argument("code");
        int res = Printer.printQrCode(code);
        result.success(res);
        break;
      }
      case "printBitmap": {
        final String filename = call.argument("filename");

        try {
          int res = Printer.printBitmap(filename);
          result.success(res);
        } catch (IOException e) {
          result.error(null, e.getMessage(), null);
        }
        break;
      }
      case "printSimboReceipt": {
        HashMap<String, Object> args = call.arguments();

        int res = Printer.printSimboReceipt(args);
        result.success(res);
        break;
      }
      case "appendText": {
        final String text = call.argument("code");
        Printer.appendText(text);
        break;
      }
      case "appendBitmap": {
        final String text = call.argument("code");

        try {
          Printer.appendBitmap(text);
        } catch (IOException e) {
          result.error(null, e.getMessage(), null);
        }
        break;
      }
      case "appendQrCode": {
        final String text = call.argument("code");
        Printer.appendQrCode(text);
        break;
      }
      case "appendScript": {
        final String code = call.argument("script");
        Printer.appendScript(code);
        break;
      }
      default:
        result.notImplemented();
        break;
    }
  }
}
