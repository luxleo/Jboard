# Enumerate로 Enum 타입 데이터 기본값 설정하기
    1. @PrePersist로 필드 널 값 체크해서 기본값 할당하는 방식
# js
    1. button submit 이벤트시 e.preventDefault() === submit handler: return false;
        두개 모두 같은 기능이다.
# Jpa
    @Unique 제약 조건 있을시 null 값도 중복 카운트 처리한다.
# API 이용하기
    1. 도로명 주소로 zipcode찾는 방법: API를 이용하자 ㅎㅎ
# MVC
    1. thymeleaf에서 th:object로 받는 object가 정의 되어있으면 컨트롤러에서
        get 방식 메소드라도 넘겨 주어야한다.
# database 저장시 
    1. 암호화 sha1, sha2(hash)+salt 알고리즘
# POJO
    1. null 선언 :
        User user = null;
        (찾는로직
        user = new User();
        user.setUID(rs.getString(1))
        )
# Todo
    1. 로그인 처리 어떻게 할 것인지... 
# paging 처리
    1. select * from post limit *,* (offset,pagesize);
        의 offset을 쿼리파라미터로 받아서 처리한다.
# 관계 매핑
    @OneToOne, @ManyToOne + @JoinColumn(name= "테이블의 필드 네임")