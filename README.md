# 집계사장
소상공인/자영업자 비즈니스 통합 MSA 플랫폼, 집계사장

<br>

## 🦀 프로젝트 소개
집계사장은 소상공인/자영업자의 쉽고 빠른 비즈니스 관리를 돕는 Cloud Native 애플리케이션입니다.

<br>

## 🍔 프로젝트 배경
소상공인/자영업자는
- 직원 출퇴근 관리
- 직원 급여 관리 및 급여 명세서 발송
- 매/지출 관리 및 문서 작성 <br>

등의 업무에서 불편을 겪고 있습니다. <br>

집계사장은 이러한 불편을 해소하고 쉽고 빠른 비즈니스 관리를 돕기 위해 개발되었습니다.

![집계사장](https://github.com/user-attachments/assets/83aad718-4068-42a3-8879-78321debb413)

집계사장은 소상공인/자영업자 분들의 불편함을 해결하며, 상생금융의 가치를 실천하고자 개발된
BaaS 기반 임베디드 금융 서비스입니다. 

![image](https://github.com/user-attachments/assets/bd13d2e3-b413-4334-a030-5ac42d3326c7)



<br>

## 🍟 기술 스택
![집계사장_기술스택](https://github.com/user-attachments/assets/40e36fb6-c2e5-45cb-8efd-16cc133154e8)

## 🥤 프로젝트 설계도
![집계사장 아키텍처](https://github.com/user-attachments/assets/22a99e16-4d6e-49a2-9a46-4053a40bda80)

<br>

## 🦀 시연 영상
링크를 누르시면 유튜브로 연결됩니다. <br>
[집계사장 시연 영상](https://youtu.be/G0IX0aQYYmw)

<br>

## 🍟 주요 기능

(은행 사 API를 바탕으로 코어 뱅킹을 자체적으로 구축하고, 이를 활용하였습니다)

# 가게 등록
- 우리 은행을 사업자 계좌로 사용하는 사업장만 등록이 가능합니다.
- 사업자 정보 검증, 계좌 정보 검증, 이메일을 통한 본인 인증, PIN 번호 활용 인증의 4단계 인증 후 가게 등록이 가능합니다.
- 카카오맵 API를 활용하여 가게의 현재 위치를 입력 받게 됩니다(위도, 경도)

# 매, 지출 내역 조회
- 자체 구축한 코어 뱅킹을 통해 해당 사업장의 매,지출 내역 차트를 확인할 수 있습니다.
 ![image](https://github.com/user-attachments/assets/d8812662-6881-4116-809e-f5a232516519)

# 원 클릭 급여 명세서, 간편 장부 발급 
- 한번의 클릭만으로 가게의 해당 월 급여 명세서, 간편 장부를 발급받을 수 있습니다.
![image](https://github.com/user-attachments/assets/f5bd0708-8ba8-46a5-ace1-1d279bc1db4c)

# 직원 출, 퇴근 관리
- 가게별 직원을 등록하고, 해당 직원의 출 퇴근 내역을 확인할 수 있습니다.
- 카카오맵 API를 활용하여 별도의 출,퇴근 용 기기 설치 없이 원격 출,퇴근이 가능합니다.
- 해당 사업장의 운영자는 직원의 출,퇴근 내역을 수정, 삭제할 수 있습니다.

# 급여 계산, 자동 이체
- 직원의 출, 퇴근 내역을 바탕으로 직원의 급여를 계산하게 됩니다.
- Spring Batch를 활용하여 급여, 주휴 수당 등의 계산이 포함 된 급여 자동 이체가 이루어집니다.
- 자동 이체 진행 시 이에 대한 급여 명세서가 직원의 이메일로 발송되게 됩니다.

# 컨테이너 오케스트레이션
- 사용자, 출,퇴근, 매,지출 관리를 담당하는 각 마이크로 서비스들은 ECS를 통해 오케스트레이션 됩니다.
- CloudMap, Route53를 활용하여 서비스 디스커버리가 이루어집니다. 

# CDC
- Debezium, Kafka를 활용하여 CDC를 구축하였습니다.
- CDC는 사용자, 출 퇴근 DB 간을 연동합니다.
- 이를 통해 마이크로 서비스간의 데이터 정합성 문제를 해결하고자 노력하였습니다.
  ![image](https://github.com/user-attachments/assets/d6d3ca99-ae2d-4e5f-b461-fec0404733da)

<br>

## 🦀 프로젝트 구조
```tree
├── .github
│   └── workflows
├── API-Gateway
├── Attendance
├── Eureka
├── Finance
├── User
├── config-server
├── core-bank

- 배포 이전 구조

```tree
├── .github
│   └── workflows
├── API-Gateway
├── Attendance
├── Finance
├── User
├── core-bank

- 배포 이후 구조
<br>

## 🦀 사용자 인터페이스 설계서
[집계사장_사용자_인터페이스_설계서.pdf](https://github.com/user-attachments/files/18120315/_._._.pdf)
<br>
## 🍔 멤버
<table>
 <tr>
   <td height="140px" align="center"> <a href="https://github.com/jihyuk0414"> <img src="https://avatars.githubusercontent.com/u/123541776?v=4" width="140px" />
     <br /> 임지혁</a></td>
   <td height="140px" align="center"> <a href="https://github.com/hyeri1126"> <img src="https://avatars.githubusercontent.com/u/114209093?v=4" width="140px" />
     <br /> 류혜리</a></td>
   <td height="140px" align="center"> <a href="https://github.com/ksp0814"> <img src="https://avatars.githubusercontent.com/u/122997638?v=4" width="140px" />
     <br /> 강세필</a></td>
   <td height="140px" align="center"> <a href="https://github.com/gusdk19"> <img src="https://avatars.githubusercontent.com/u/128590006?v=4" width="140px" />
     <br /> 이현아</a></td>
   <td height="140px" align="center"> <a href="https://github.com/my123dsa"> <img src="https://avatars.githubusercontent.com/u/174989195?v=4" width="140px" />
     <br /> 박준현</a></td>
   <td height="140px" align="center"> <a href="https://github.com/apple6346654"> <img src="https://avatars.githubusercontent.com/u/174989500?v=4" width="140px" />
     <br /> 정성윤</a></td>
 </tr>
 <tr>
   <td align="center">Backend</td>
   <td align="center">Backend</td>
   <td align="center">Frontend</td>
   <td align="center">Frontend</td>
   <td align="center">Frontend</td>
   <td align="center">Frontend</td>
 </tr>
  <tr>
   <td align="center">기여 부</td>
   <td align="center">기여 부</td>
   <td align="center">기여 부</td>
   <td align="center">기여 부</td>
   <td align="center">기여 부</td>
   <td align="center">기여 부</td>
 </tr>
</table>
<br>
<br>
