play-scala-ws
=============

Play framework 2.3( + Scala 2.11) 에서 websocket 사용 테스트




## Version

- JDK : Java SE 1.7.0.45
- SBT : 0.13.5
- Play : 2.3
- Scala : 2.11.1




## 구성 방법

- Play framework를 압축 해제 (C:\Project\play)

- activation 실행 
    C:\Project\play>activation new play-scala-ws
    
    $>4. scala 선택

- 해당 프로젝트 activation 실행

    C:\Project\play\play-scala-ws>activation
    
    $> update
    
    $> eclipse
        
    $> ~run

   
   * java.lang.OutOfMemoryError: PermGen space 오류 발생시 환경 변수 등록(메모리 사용량은 적절히)   
     SET JAVA_OPTS=-Xmx512m -XX:PermSize=1024M -XX:MaxPermSize=4096M
      
     
- 이클립스에서 import








