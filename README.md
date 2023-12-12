# Off The Lamp

> 자동차 극장 소개 및 예매 사이트<br>
> 자동차 극장의 위치 및 간단한 정보를 한눈에 볼 수 있으며 영화를 예매하고 결제할 수 있는 사이트를 구현한 JSP MVC 모델2 프로젝트

- 프로젝트 기간 : 2023.08.24 ~ 2023.09.27
- 개발 인원 : 6명(팀프로젝트)
- 역할 : 메인페이지, 예매, 결제, 예매내역
- 개발 환경 : JSPMVC2 Model을 활용하여 개발
- 사용 기술

  <table border = 1>
     <tr>
        <td>백엔드 </td>
        <td> JSP / Java  </td>
     </tr>
     <tr>
        <td>프론트엔드 </td>
        <td> HTML / CSS / JavaScript / JQuery / Ajax / JSON  </td>
     </tr>
     <tr>
        <td>DB </td>
        <td> MySQL  </td>
     </tr>
     <tr>
        <td>서버 </td>
        <td> Tomcat </td>
     </tr>
     <tr>
        <td>API </td>
        <td> 카카오 간편로그인 / 네이버 지도 / PortOne 결제 API </td>
     </tr>
     <tr>
        <td>협업 도구 </td>
        <td> Git / Github / Notion / Discord  </td>
     </tr>
  </table>
---
## 프로젝트 자료 
- [프로젝트 ppt](https://drive.google.com/file/d/15zxT7guDY57XbQDC7Om5K1Oddz8oPTxv/view?usp=sharing)
- [요구사항 명세서](https://docs.google.com/spreadsheets/d/121b-0Syd5_DSfEWAOKdMIVIs-jJyTUfOhzT2vW8vjnA/edit?usp=sharing)
- [ERD](https://drive.google.com/file/d/1CpGhDHFUAILwhrDFHKbwBACwU4BPW9W9/view?usp=sharing)
---
## 포르젝트 시연
#### 메인페이지 구현
<img src="https://github.com/yejively/OffTheLamp/assets/143873963/b9c832e5-e40e-441d-b0e9-a917bc5f643f.gif" width="700" heigth="500">

---
#### 자동차극장 예매 구현
<img src="https://github.com/yejively/OffTheLamp/assets/143873963/bcde3aa1-b7c7-4104-92ba-d9bd39a7039f.gif" width="700" heigth="500">

💡 예매페이지
- 지역 > 극장 > 영화 > 시간 ajax 비동기 처리로 선택 시 해당하는 내용을 구현
- 자동차 극장의 특성상 차량의 크기에 따라 차량 배치를 위해 차량 정보를 등록
- jquery를 사용해 예매정보, 차량 정보가 없을 시 알림창을 통해 접근제한

💡 결제페이지
- 예매페이지에서 전달받은 데이터 출력
- 기존에 팔린 좌석들의 데이터를 전달받아 처리
```Javascript
	function showSeat(){	
		var getSeat = "${param.seat}";
		var seatList = getSeat.split(',');	// getSeat를 ,로구분해 배열로 저장
			
		for(var i=0; i<seatList.length-1; i++){
			for(var j=0; j<40; j++){		
				if($("#"+j).text()==seatList[i]){
					$('#'+j).css('backgroundColor','black');
					$('#'+j).addClass("xxseat");
					$('#'+j).off('click');	// 팔린좌석은 선택안됨.
				}
			}
		}
	}
```
</details>

- 좌석 선택 > 결제 api 포트원을 이용해 통합 결제 연동 구현

💡 마이페이지 예매내역
- 정상적인 결제 진행 후 마이페이지 > 예매내역 이동
- 예매내역 리스트형식으로 출력
- 상세보기 클릭 시 상세내용 출력을 모달로 구현
- 결제 완료 시 모달에 결제취소 버튼 구현 - 결제 취소 시 상태 변경 구현
---

  


