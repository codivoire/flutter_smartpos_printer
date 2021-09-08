package com.codivoire.flutter_smartpos_printer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.whty.smartpos.tysmartposapi.ITYSmartPosApi;
import com.whty.smartpos.tysmartposapi.modules.printer.PrintElement;
import com.whty.smartpos.tysmartposapi.modules.printer.PrinterConfig;
import com.whty.smartpos.tysmartposapi.modules.printer.PrinterConstrants;
import com.whty.smartpos.tysmartposapi.modules.printer.PrinterListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Printer {
    @SuppressLint("StaticFieldLeak")
    private static Activity activity;
    private static ITYSmartPosApi smartPosApi;
    private static final StringBuffer result = new StringBuffer();

    public static void setActivity(Activity activity) {
        Printer.activity = activity;
    }

    public static void setContext(Context appContext) {
        Printer.smartPosApi = ITYSmartPosApi.get(appContext);
        smartPosApi.setListener(new Listener());
    }

    public static boolean initPrinter() {
        boolean res = smartPosApi.initPrinter();
        smartPosApi.setPrinterListener(new MyPrinterListener());

        return res;
    }

    public static void releasePrinter() {
        smartPosApi.releasePrinter();
        smartPosApi.setPrinterListener(null);
        smartPosApi.setListener(null);
    }

    public static boolean setPrintParameters(int fontSize, int align, int fontWeight) {
        Bundle bundle = new Bundle();
        bundle.putInt(PrinterConfig.FONT_SIZE, fontSize | PrinterConstrants.FONT_SIZE_LARGE);
        bundle.putInt(PrinterConfig.ALIGN, align | PrinterConstrants.ALIGN_CENTER);
        bundle.putInt(PrinterConfig.CN_FONT, fontWeight | PrinterConstrants.SONGTI_BOLD);
        bundle.putInt(PrinterConfig.EN_FONT, fontWeight | PrinterConstrants.SONGTI_BOLD);
        return smartPosApi.setPrintParameters(bundle);
    }

    public static int getStatus() {
        return smartPosApi.getStatus();
    }

    public static boolean setYSpace(int space) {
        return smartPosApi.setYSpace(space);
    }

    public static int feedPaper(int space) {
        return smartPosApi.feedPaper(space);
    }

    public static void startPrint() {
        smartPosApi.startPrint();
    }

    public static int printText(String text) {
        return smartPosApi.printText(text);
    }

    public static int printQrCode(String code) {
        return smartPosApi.printBarcode(PrinterConstrants.QR_CODE, code);
    }

    public static int printBitmap(String fileName) throws IOException {
        InputStream is = activity.getAssets().open(fileName);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return smartPosApi.printBitmap(bitmap);
    }

    public static int printSimboReceipt(HashMap<String, Object> args) {
        String montant = (String) args.get("montant");
        String reference = (String) args.get("reference");
        String date = (String) args.get("date");
        String libelle = (String) args.get("libelle");
        String ville = (String) args.get("ville");
        String region = (String) args.get("region");
        String ctb = (String) args.get("ctb");
        String ref = (String) args.get("ref");
        String fax = (String) args.get("fax");
        String tel = (String) args.get("tel");
        String contribuable = (String) args.get("contribuable");
        String matricule = (String) args.get("matricule");
        String equipement = (String) args.get("equipement");
        String agent = (String) args.get("agent");

        // smartPosApi.setYSpace(10);
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(
                new PrintElement(ville, PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_MIDDLE));
        smartPosApi.appendPrintElement(
                new PrintElement(region, PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("---------------------------", PrinterConstrants.ALIGN_CENTER));
        smartPosApi.appendPrintElement(new PrintElement("CTB: " + ctb + " / REF: " + ref,
                PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("TEL: " + tel + " / FAX: " + fax,
                PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("---------------------------", PrinterConstrants.ALIGN_CENTER));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(
                new PrintElement(libelle, PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_MIDDLE));
        smartPosApi.appendPrintElement(
                new PrintElement(reference, PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_MIDDLE));
        smartPosApi.appendPrintElement(
                new PrintElement(date, PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(
                new PrintElement(montant + " FCFA", PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_HUGE));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi
                .appendPrintElement(new PrintElement("------------------------------", PrinterConstrants.ALIGN_CENTER));
        smartPosApi.appendPrintElement(new PrintElement("Contribuable : " + contribuable,
                PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("Matricule : " + matricule, PrinterConstrants.ALIGN_CENTER,
                PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("Equipement : " + equipement, PrinterConstrants.ALIGN_CENTER,
                PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi.appendPrintElement(new PrintElement("Agent : " + agent, PrinterConstrants.ALIGN_CENTER,
                PrinterConstrants.FONT_SIZE_SMALL));
        smartPosApi
                .appendPrintElement(new PrintElement("------------------------------", PrinterConstrants.ALIGN_CENTER));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(new PrintElement("Powered by ORBIT", PrinterConstrants.ALIGN_CENTER));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(new PrintElement("    "));
        smartPosApi.appendPrintElement(new PrintElement("    "));

        return smartPosApi.startPrintElement();
    }

    public static void appendText(String text) {
        smartPosApi.appendText(text);
    }

    public static void appendBitmap(String fileName) throws IOException {
        InputStream is = activity.getAssets().open(fileName);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        smartPosApi.appendBitmap(bitmap);
    }

    public static void appendScript(String script) {
        smartPosApi.appendScript(script);
    }

    public static void appendQrCode(String code) {
        smartPosApi.appendBarcode(PrinterConstrants.QR_CODE, code);
    }

    private static String getPicture(Bitmap bitmap) {
        return Base64.encodeToString(bitmap2Bytes(bitmap), Base64.DEFAULT);
    }

    private static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap newbitmap = Bitmap.createScaledBitmap(bitmap, 300, 130, true);
        newbitmap.compress(Bitmap.CompressFormat.PNG, 20, baos);
        newbitmap.recycle();
        return baos.toByteArray();
    }

    private static String execute(int index) {
        result.delete(0, result.length());

        switch (index) {
            case 0:
                result.append(">>> initPrinter <<<\n");
                boolean res = smartPosApi.initPrinter();
                smartPosApi.setPrinterListener(new MyPrinterListener());
                result.append("result : ").append(res).append("\n");
                break;
            case 1:
                result.append(">>> setPrintParameters <<<\n");
                Bundle bundle = new Bundle();
                bundle.putInt(PrinterConfig.FONT_SIZE, PrinterConstrants.FONT_SIZE_LARGE);
                bundle.putInt(PrinterConfig.ALIGN, PrinterConstrants.ALIGN_CENTER);
                bundle.putInt(PrinterConfig.CN_FONT, PrinterConstrants.SONGTI_BOLD);
                bundle.putInt(PrinterConfig.EN_FONT, PrinterConstrants.SONGTI_BOLD);
                res = smartPosApi.setPrintParameters(bundle);
                result.append("result : ").append(res).append("\n");
                break;
            case 2:
                result.append(">>> setYSpace <<<\n");
                result.append("param ySpace : " + 50 + "\n");
                res = smartPosApi.setYSpace(50);
                result.append("result : ").append(res).append("\n");
                break;
            case 3:
                result.append(">>> printText <<<\n");
                int ret = smartPosApi.printText("abcdefghijklmn\nopqrstuvwxyz 0123456789");
                result.append("result : ").append(ret).append("\n");
                break;
            case 4:
                result.append(">>> feedPaper <<<\n");
                result.append("param value : " + 5000 + "\n");
                ret = smartPosApi.feedPaper(5000);
                result.append("result : ").append(ret).append("\n");
                break;
            case 5:
                try {
                    result.append(">>> printBitmap <<<\n");
                    InputStream is = activity.getAssets().open("barcode.bmp");
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    ret = smartPosApi.printBitmap(bitmap);
                    result.append("result : ").append(ret).append("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                result.append(">>> printBarcode <<<\n");
                result.append("param barcode : " + "123456" + "\n");
                ret = smartPosApi.printBarcode(PrinterConstrants.QR_CODE, "654654641218654968765465463131643154984");
                result.append("result : ").append(ret).append("\n");
                break;
            case 7:
                result.append(">>> printTemplate <<<\n");
                Map<String, String> data = new HashMap<>();
                data.put("SlipName", "POS签购单");
                data.put("MerNum", "2017666888");
                data.put("TerNum", "10072686");
                data.put("CardNum", "429006****2115");
                data.put("TradeType", "消费");
                data.put("BatchNum", "000001");
                data.put("VoucherNum", "000003");
                data.put("RefNum", "1223346546546");
                data.put("TradeDate", "2017/03/07 11:11:11");
                data.put("Amount", "16.80");
                InputStream is = null;
                try {
                    is = activity.getAssets().open("signature.png");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                String base64Str = getPicture(bitmap);
                data.put("CardHolderSignature", base64Str);
                smartPosApi.printTemplate(data, "template", 0, 5000, 0);
                break;
            case 8:
                result.append(">>> printCustomTemplate <<<\n");
                smartPosApi.printCustomTemplate(null, "", "", 5000);
                break;
            case 9:
                result.append(">>> getStatus <<<\n");
                ret = smartPosApi.getStatus();
                result.append("result : ").append(ret).append("\n");
                break;
            case 10:
                result.append(">>> appendText <<<\n");
                result.append("param text : " + "天喻信息\n智能POS专用测试Demo" + "\n");
                smartPosApi.appendText("天喻信息智能POS专用测试Demo");
                break;
            case 11:
                try {
                    result.append(">>> appendBitmap <<<\n");
                    is = activity.getAssets().open("Tousei_logo_print.bmp");
                    bitmap = BitmapFactory.decodeStream(is);
                    smartPosApi.appendBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 12:
                result.append(">>> appendBarcode <<<\n");
                result.append("param barcode : " + "123456" + "\n");
                smartPosApi.appendBarcode(PrinterConstrants.QR_CODE, "123456");
                break;
            case 13:
                result.append(">>> appendScript <<<\n");
                smartPosApi.appendScript("!gray 1\n" + "!yspace 150\n" + "!hz l\n" + "!asc l\n" + "*text c    \n"
                        + "*text c    \n" + "*text c    \n" + "!NLFONT hz 0\n" + "!NLFONT asc 0\n"
                        + "*text l 商户名称:天喻测试商户\n" + "*text l 商户编号:123456789012345\n" + "*line\n"
                        + "*text l 转让方:  彭*(持卡人)\n" + "*text l 班次号:201707311234\n" + "*text l 发卡行:招商银行  收单行:中国银行\n"
                        + "*text l 有效期:20190731\n" + "*text l 卡号:招商银行\n" + "!hz l\n" + "!asc l\n" + "!gray 8\n"
                        + "*text l 6225 75******5838/C\n" + "!hz l\n" + "!asc l\n" + "!gray 8\n" + "*text l 交易类型:消费\n"
                        + "!hz n\n" + "!asc n\n" + "!gray 8\n" + "*text l 凭证号:000001 授权码:415745\n"
                        + "*text l 批次号:000045 参考号:458612356325\n" + "!hz n\n" + "!asc n\n" + "!gray 8\n"
                        + "*text l 交易日期:2014/07/31 14:22:02\n" + "!hz n\n" + "!asc n\n" + "!gray 8\n" + "*text l 金额:\n"
                        + "!hz l\n" + "!asc l\n" + "!gray 8\n" + "*text l    RMB:0.01\n" + "!hz n\n" + "!asc n\n"
                        + "!gray 8\n" + "*text l 备 注:\n" + "!hz sn\n" + "!asc sn\n" + "!gray 5\n"
                        + "*text c ...............................................\n" + "!hz n\n" + "!asc n\n"
                        + "!gray 8\n" + "*text c    \n" + "*text c    \n" + "*text c    \n" + "*text l 持卡人签名:\n"
                        + "*text c    \n" + "*text c    \n" + "*text c    \n" + "*text c    \n" + "*text c    \n");
                break;
            case 14:
                result.append(">>> startPrint <<<\n");
                smartPosApi.startPrint();
                break;
            case 15:
                result.append(">>> startPrintElement <<<\n");
                // smartPosApi.setTypeface(null);
                smartPosApi.setYSpace(10);
                smartPosApi.appendPrintElement(new PrintElement("Tap hoa", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(new PrintElement("***", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(new PrintElement("    "));
                smartPosApi.appendPrintElement(new PrintElement("Default", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(new PrintElement("0987033515", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(new PrintElement("    "));
                smartPosApi.appendPrintElement(
                        new PrintElement("INVOICE", PrinterConstrants.ALIGN_CENTER, PrinterConstrants.FONT_SIZE_LARGE));
                smartPosApi.appendPrintElement(new PrintElement("    "));
                smartPosApi.appendPrintElement(new PrintElement("Code        : 1C1D98F3CF0344399848F29EF7A3CA87"));
                smartPosApi.appendPrintElement(new PrintElement("Serve       : Dine in"));
                smartPosApi.appendPrintElement(new PrintElement("Date        : 21-01-2021 - 14:49"));
                smartPosApi.appendPrintElement(new PrintElement("Cashier     : Pham Thi Thao"));
                smartPosApi.appendPrintElement(
                        new PrintElement("------------------------------", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(
                        new PrintElement("Item             Qty      Total", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(
                        new PrintElement("------------------------------", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(
                        new PrintElement("[CB] Combo 1      1     500,000", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(
                        new PrintElement("- tra chanh       1            ", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(
                        new PrintElement("------------------------------", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(new PrintElement("    "));
                smartPosApi.appendPrintElement(
                        new PrintElement("NextOrder Powered by LOOP SMART POS", PrinterConstrants.ALIGN_CENTER));
                smartPosApi.appendPrintElement(new PrintElement("    "));
                smartPosApi.appendPrintElement(new PrintElement("    "));
                smartPosApi.startPrintElement();
                break;
            case 16:
                result.append(">>> releasePrinter <<<\n");
                smartPosApi.releasePrinter();
                smartPosApi.setPrinterListener(null);
                break;
        }

        return result.toString();
    }

    static class MyPrinterListener implements PrinterListener {
        @Override
        public void onPrinterOutOfPaper() {
        }

        @Override
        public void onPrinterStart(String templateName) {
        }

        @Override
        public void onPrinterEnd(String templateName) {
        }

        @Override
        public void onPrinterError(String msg) {
        }
    }
}
