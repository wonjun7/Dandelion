1. build.gradle 에서 gradle 정보 받기

2. back_end\src\main\resources\application.properties 수정

- 마리아 디비 id, pw 맞게 수정

3. 해당 마리아 디비에 dandelion db 생성

4. back_end\src\main\resources\table_make_sql.txt db 에서 실행

- 테이블 정보 및 지점 보관함, 보관함 임시 데이터 생성

5. back_end\src\main\resources\mariadb_procedures_실행시 확인.txt db 에서 실행

-- 마리아 디비 이벤트 작동 설정 on 으로 설정하기 위한 것

6. back_end\src\main\resources\check_update(procedures).txt db 에서 실행

- 해당 db에 프로시저 정보 저장

7. back_end\src\main\resources\call_test(event).txt db 에서 실행

- 해당 event 실행 정보 저장

8. 내장된 tomcat 실행 ( 9090 작동 )