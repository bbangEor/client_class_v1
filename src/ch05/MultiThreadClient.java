package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// 1. 함수로 분리해서 리팩토링 진행
public class MultiThreadClient {

	public static void main(String[] args) {

		System.out.println(" >>>> 클라이언트 실행 <<<< ");

		try (Socket socket = new Socket("localhost", 5000)) {
			System.out.println("connected to the server !");

			// 서버와 통신을 위한 스트림 초기화
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

			startReadThread(bufferedReader);
			startWriteThread(printWriter, keyboardReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 클라이언트로 부터 데이터를 읽는 스레드 분리
	private static void startReadThread(BufferedReader bufferedReader) {
		Thread readThread = new Thread(() -> {
			try {
				String Message;
				while ((Message = bufferedReader.readLine()) != null) {

					System.out.println(" 클라이언트에게서 온 메세지 : " + Message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		readThread.start();
	}

	// 키보드에서 입력을 받아 클라이언트 측으로 데이터를 전송하는 스레드
	private static void startWriteThread(PrintWriter writer, BufferedReader keyboardReader) {
		Thread writeThread = new Thread(() -> {
			try {

				String Message;
				while ((Message = keyboardReader.readLine()) != null) {

					System.out.println(" Server 에게서 온 메세지 : " + Message);
					writer.println();
					writer.flush();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		writeThread.start();
		try {
			writeThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
