//package com.example.myceleb;
//
//import java.io.*;
//
//public class ParsingText {
//    public static void main(String[] args) {
//
//
//        String inputFilePath = "1.txt";
//        String outputFilePath = "1!.txt";
//
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                // 마침표 뒤에 @ 추가
//                String modifiedLine = addAtAfterDot(line);
//                // '.@\"'를 '.\"@'로 변경
//                String finalModifiedLine = modifyText(modifiedLine);
//                writer.write(finalModifiedLine);
//                writer.newLine();
//            }
//
//            reader.close();
//            writer.close();
//
//            System.out.println("작업 완료");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String addAtAfterDot(String line) {
//        // 마침표 뒤에 @ 추가
//        return line.replaceAll("\\.\\s*", ".@");
//    }
//
//    private static String modifyText(String line) {
//        // '.@\"'를 '.\"@'로 변경
//        return line.replace(".@\"", ".\"@");
//    }
//}
