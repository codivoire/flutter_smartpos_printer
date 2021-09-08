package com.codivoire.flutter_smartpos_printer;

import com.whty.smartpos.tysmartposapi.ISmartPosListener;
import com.whty.smartpos.tysmartposapi.OperationResult;
import com.whty.smartpos.tysmartposapi.inner.model.Balance;
import com.whty.smartpos.tysmartposapi.inner.model.EMVCardLog;
import com.whty.smartpos.tysmartposapi.inner.model.EMVTermConfig;
import com.whty.smartpos.tysmartposapi.modules.cardreader.CardInfo;
import com.whty.smartpos.tysmartposapi.modules.pinpad.PinResult;
import com.whty.smartpos.tysmartposapi.modules.pos.inner.DeviceInfo;
import com.whty.smartpos.tysmartposapi.modules.pos.inner.DeviceVersion;
import com.whty.smartpos.tysmartposapi.modules.scanner.ScanResult;

import java.util.List;

public class Listener extends ISmartPosListener {

    @Override
    public void onInitPrinter() {

    }

    @Override
    public void onReleasePrinter() {

    }

    @Override
    public void onSetPrintParameters(boolean isSuccess) {

    }

    @Override
    public void onSetYSpace(boolean isSuccess) {

    }

    @Override
    public void onGetStatus(int status) {

    }

    @Override
    public void onReadCardType(CardInfo cardInfo) {

    }

    @Override
    public void onReadCard(CardInfo cardInfo) {

    }

    @Override
    public void onCancelReadCard(boolean b) {

    }

    @Override
    public void onStartSearchCard(int i) {

    }

    @Override
    public void onStopSearchCard(int i) {

    }

    @Override
    public void onGetMagCardData(CardInfo cardInfo) {

    }

    @Override
    public void onPowerOn(OperationResult operationResult) {

    }

    @Override
    public void onPowerOff(int i) {

    }

    @Override
    public void onHalt(int i) {

    }

    @Override
    public void onActive(int i) {

    }

    @Override
    public void onIsCardExists(int i) {

    }

    @Override
    public void onIsRfCardExists(int i) {

    }

    @Override
    public void onTransmitIC(OperationResult operationResult) {

    }

    @Override
    public void onTransmitRF(OperationResult operationResult) {

    }

    @Override
    public void onTransmitPSAM(OperationResult operationResult) {

    }

    @Override
    public void onOpenPSAMCard(OperationResult operationResult) {

    }

    @Override
    public void onClosePSAMCard(int i) {

    }

    @Override
    public void onVerifyKeyA(int i) {

    }

    @Override
    public void onVerifyKeyB(int i) {

    }

    @Override
    public void onReadBlock(OperationResult operationResult) {

    }

    @Override
    public void onWriteBlock(int i) {

    }

    @Override
    public void onWriteValue(int i) {

    }

    @Override
    public void onReadValue(OperationResult operationResult) {

    }

    @Override
    public void onIncreaseValue(int i) {

    }

    @Override
    public void onDecreaseValue(int i) {

    }

    @Override
    public void onReadMifareUltralightCard(OperationResult operationResult) {

    }

    @Override
    public void onWriteMifareUltralightCard(int i) {

    }

    @Override
    public void onTradeResponse(int i) {

    }

    @Override
    public void onClearAID(boolean b) {

    }

    @Override
    public void onClearIcAID(boolean b) {

    }

    @Override
    public void onClearRfAID(boolean b) {

    }

    @Override
    public void onClearCAPK(boolean b) {

    }

    @Override
    public void onUpdateAID(boolean b) {

    }

    @Override
    public void onUpdateIcAID(boolean b) {

    }

    @Override
    public void onUpdateRfAID(boolean b) {

    }

    @Override
    public void onUpdateCAPK(boolean b) {

    }

    @Override
    public void onGetAID(List<byte[]> list) {

    }

    @Override
    public void onGetIcAID(List<byte[]> list) {

    }

    @Override
    public void onGetRfAID(List<byte[]> list) {

    }

    @Override
    public void onGetCAPK(List<byte[]> list) {

    }

    @Override
    public void onGetTlv(byte[] bytes) {

    }

    @Override
    public void onGetTlvValue(byte[] bytes) {

    }

    @Override
    public void onGetTlvList(byte[] bytes) {

    }

    @Override
    public void onSetTlv(int i) {

    }

    @Override
    public void onQueryECBalance(Balance balance) {

    }

    @Override
    public void onGetEmvCardLog(List<EMVCardLog> list) {

    }

    @Override
    public void onGetTlvEncrypted(String s) {

    }

    @Override
    public void onGetTlvValueEncrypted(String s) {

    }

    @Override
    public void onGetTlvListEncrypted(String s) {

    }

    @Override
    public void onSetLed(int i, boolean b) {

    }

    @Override
    public void onSelectKeyGroup(int i) {

    }

    @Override
    public void onUpdateMainKey(int i) {

    }

    @Override
    public void onUpdateWorkKey(int i) {

    }

    @Override
    public void onUpdateTransKey(int i) {

    }

    @Override
    public void onCalculateMac(OperationResult operationResult) {

    }

    @Override
    public void onGetRandom(OperationResult operationResult) {

    }

    @Override
    public void onEncryptData(OperationResult operationResult) {

    }

    @Override
    public void onEncryptKSN(OperationResult operationResult) {

    }

    @Override
    public void onKeyDown(int i) {

    }

    @Override
    public void onGetPinResult(PinResult pinResult) {

    }

    @Override
    public void onDukptUpdateKSN(int i) {

    }

    @Override
    public void onDukptUpdateIPEK(int i) {

    }

    @Override
    public void onGetEncPinblock(OperationResult operationResult) {

    }

    @Override
    public void onGetDeviceSN(String s) {

    }

    @Override
    public void onGetDevicePN(OperationResult operationResult) {

    }

    @Override
    public void onGetDeviceKSN(OperationResult operationResult) {

    }

    @Override
    public void onGetDeviceVersion(DeviceVersion deviceVersion) {

    }

    @Override
    public void onGetDeviceInfo(DeviceInfo deviceInfo) {

    }

    @Override
    public void onSetTermConfig(boolean b) {

    }

    @Override
    public void onGetTermConfig(EMVTermConfig emvTermConfig) {

    }

    @Override
    public void onGetMerchantAndTerminalNo(String[] strings) {

    }

    @Override
    public void onUpdateMerchantAndTerminalNo(int i) {

    }

    @Override
    public void onGetBatchAndFlowNo(String[] strings) {

    }

    @Override
    public void onUpdateBatchAndFlowNo(int i) {

    }

    @Override
    public void onInstallApp(boolean b) {

    }

    @Override
    public void onUninstallApp(boolean b) {

    }

    @Override
    public void onSetSystemClock(boolean b) {

    }

    @Override
    public void onDisableStatusBar(boolean b) {

    }

    @Override
    public void onSetAPN(boolean b) {

    }

    @Override
    public void onWriteCustomData(int i) {

    }

    @Override
    public void onReadCustomData(OperationResult operationResult) {

    }

    @Override
    public void onTransceive(OperationResult operationResult) {

    }

    @Override
    public void onSecurityChipStarted(byte[] bytes) {

    }

    @Override
    public void onScanResult(ScanResult scanResult) {

    }
}
