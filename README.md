# testShoppingWeb
인프런 김영한님의 강의 수강 후, 바탕으로 작은 쇼핑몰 웹 사이트를 구현하는 토이 프로젝트입니다.

# Dependencies
- ThymeLeaf
- Lombok
- Spring Web
- Validation
- Spring Data JPA
- H2 Database
- QueryDSL
- p6spy



# 22.01.20
- 환경 설정 완료
- 주요 엔티티 연관관계 설정
- Order 엔티티 비즈니스 로직 개발 및 테스트 코드 작성

# 22.01.21
- Member 도메인 비즈니스 로직 개발 : 휴대폰 전화번호 데이터 정규화하여 저장하기.
- Member 도메인 비즈니스 테스트 코드 추가 : 휴대폰 전화번호 데이터 정규화하여 저장하기.
- MemberRepository 개발 : 회원 단 건 조회, 회원 전체 조회, 회원 이름으로 검색하기
- MemberRepository 개발 : 회원 저장 시, 중복 이름 가진 사람 걸러내는 로직 추가.
- MemberRepository 테스트 코드 작성
- MemberService 개발 : 회원 저장, 회원 단 건 조회, 회원 전체 조회, 회원 이름으로 검색하기


# 22.01.22
- Member 도메인 비즈니스 로직 개발 : 휴대폰 전화번호 데이터 정규화하여 저장하기.
- Member 도메인 비즈니스 테스트 코드 추가 : 휴대폰 전화번호 데이터 정규화하여 저장하기.
- MemberRepository 개발 : 회원 단 건 조회, 회원 전체 조회, 회원 이름으로 검색하기
- MemberRepository 개발 : 회원 저장 시, 중복 이름 가진 사람 걸러내는 로직 추가.
- MemberRepository 테스트 코드 작성
- MemberService 개발 : 회원 저장, 회원 단 건 조회, 회원 전체 조회, 회원 이름으로 검색하기
- HomeController 개발 ★★★★ HomeController 추후에 HTML 변경 시, 재구성 필요함. 
- MemberController 개발 : 회원 저장, 회원 리스트 조회 접속
- MemberControoler 내부 Validation 로직 개발 

# 22.01.23
- ItemController 개발(상품 등록, 상품 목록 조회, 상품 수정 기능 구현)
- ItemController의 Validation 기능 구현(ItemForm 객체 분리 이후 필요한 값들의 개별 Validation 설정
- OrderController의 개발(주문 등록, 주문 조회, 주문 취소 기능 구현)
- 주문 등록 시, Validation 기능 개발 중(아무런 값이 들어왔을 때, 주문 처리되지 않는 기능 구현 중) 
