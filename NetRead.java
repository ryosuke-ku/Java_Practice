//----------------------
// �l�b�g����f�[�^��ǂݎ��
//---------------------
import java.net.*;
import java.io.*;
public class NetRead {
	public static void main(String args[]){
	
		try{ //�T�˂̑���ŗ�O�������K�v�ł��B
			 //URL���쐬����
			 String adress="https://syllabus.naist.jp/subjects/preview_detail/19";
			 URL url=new URL(adress);//URL��ݒ�
			 
		     // URL�ڑ�
			  HttpURLConnection connect = (HttpURLConnection)url.openConnection();//�T�C�g�ɐڑ�
		      connect.setRequestMethod("GET");//�v���g�R���̐ݒ�
		      InputStream in=connect.getInputStream();//�t�@�C�����J��
		      
		      // �l�b�g����f�[�^�̓ǂݍ���
		      String str;//�l�b�g����ǂ񂾃f�[�^��ۊǂ���ϐ���錾
		      str=readString(in);//1�s�ǂݎ��
		      while (str!=null) {//�ǂݎ�肪�������Ă����
		        System.out.println(str);//�R���\�[���ɏo��
		        str=readString(in);//����ǂݍ���
		      }
		  
		      // URL�ؒf
		      in.close();//InputStream�����
		      connect.disconnect();//�T�C�g�̐ڑ���ؒf

		}catch(Exception e){
			//��O����������������A�\������
			System.out.println("Err ="+e);
		}
	}
	
	//InputStream���P�s�����ǂށi�ǂ߂Ȃ����null��Ԃ��j
	static String readString(InputStream in){
		try{
			int l;//�Ă񂾒������L�^
			int a;//�ǂ񂾈ꕶ���̋L�^�Ɏg��
			byte b[]=new byte[2048];//�Ă񂾃f�[�^���i�[
			a=in.read();//�P�����ǂ�
			if (a<0) return null;//�t�@�C����ǂ݂��Ă�����Anull��Ԃ�
			l=0;
			while(a>10){//�s�̏I���܂œǂ�
				if (a>=' '){//�����̕����ł���΁A�o�C�g�ɒǉ�
					b[l]=(byte)a;
					l++;
				}
				a=in.read();//����ǂ�
			}
			return new String(b,0,l);//������ɕϊ�
		}catch(IOException e){
			//Err���o����A�\������null�l��Ԃ�
			System.out.println("Err="+e);
			return null;
		}
	}
}
