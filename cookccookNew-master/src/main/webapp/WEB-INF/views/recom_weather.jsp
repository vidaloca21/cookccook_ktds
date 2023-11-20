<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>역지도</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBBShEPgTGt3ahxA6qPNnoyBA87XlQw9RY&callback=initMap&v=weekly" defer></script>
    <script>
    
    
    let Lat;
    let Lng;

    function askForLocation () {
        navigator.geolocation.getCurrentPosition(accessToGeo)
    }
    askForLocation();
    
    function accessToGeo(position) {
        lat = position.coords.latitude
        lng = position.coords.longitude
        
        console.log(lat, lng)
        
        const latlngUrl = `https://maps.googleapis.com/maps/api/geocode/json?latlng=\${lat},\${lng}&language=en&key=AIzaSyBBShEPgTGt3ahxA6qPNnoyBA87XlQw9RY`
        	   
        	
        	fetch(latlngUrl)
            .then((response) => response.json())
            .then((data) => {
              // Parse the city name from the API response
              const addressComponents = data.results[0].address_components;
              const componentsLength = addressComponents.length;
              console.log(componentsLength)
              let cityArray = [];
              
              for (let i = componentsLength - 1; i >= 0; i--) {
            	  console.log(i, addressComponents[i].types.find(str => str == "premise"));
            	  if (!addressComponents[i].types.find(str => str == "premise")) {
            		  console.log("?", addressComponents[i].long_name)
            		  cityArray.push(addressComponents[i].long_name);
            	  }
              }
              
              /* const city = data.results[0].address_components.find((component) =>
                component.types.includes("sublocality")
              ).long_name;
 */
 			  let city = cityArray.join(" ");
              console.log(`Your city is \${city}.`);
              let gu = cityArray[cityArray.length - 2];
              console.log(gu)
              
              
              const weatherApiUrl=`https://api.weatherapi.com/v1/current.json?key=ba4a067c93054f36a0912122232610 &q=\${gu}&aqi=yes`
          		
          		fetch(weatherApiUrl)
          		.then((response) => response.json())
          		.then((data) => {
          			
          			let temp = data.current.temp_c
          			
          			console.log(temp)
          			
          			const parentElement = document.getElementById("parentElement");

	          		// 추가할 새로운 HTML 요소를 생성
	          		const newElement = document.createElement("div");
	          		newElement.textContent = `\${temp}°C`;
	
	          		// 부모 요소에 새로운 요소를 추가
	          		parentElement.append(newElement);
          		}
          		
          		) 
            })
            .catch((error) => console.log(error));
        
        

        fetch('/checktest', {
          method: 'POST', 
          headers: {
            'Content-Type': 'application/json', // 데이터 형식 (JSON, XML, 등)
          },
          body: JSON.stringify({ temp }), // 전송할 데이터
        })
        .then(response => {
          if (!response.tempRecom) {
            throw new Error('네트워크 연결이 불안정합니다.');
          }
          return response.tempRecom;
        })
        .then(data => {
        	for (var i = 0; i >= tempRecom.length(); i++ ) {
        		let title = $("<div class='recomTitle'>tempRecom.title</div>")
        		let img = $("<div class='recomImg'>tempRecom.attImgSmall</div>")
        		
        		$(".recom_div").append(title)
        		$(".recom_div").append(img)
        		
        	}
          
        	
        })
        .catch(error => {
          console.error('fetch 작업 중에 문제가 발생했습니다. :', error);
        });
    	
    }
	
	// ajax 제외 post 보내는 방법: form , json 
    
   
      
    </script>
    
    
    
    <style>
      
    </style>
  </head>
  <body>
  	<div id=parentElement></div>
  	
  	<div class="recom_div"> </div>
  	
  </body>
</html>
    