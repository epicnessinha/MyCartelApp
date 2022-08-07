$(document).ready(function () {

    $('#submit').click(function(event){
        event.preventDefault();
    })
});

function doClick() {
    if($('input[name="fly"]:checked').val() === 'option1' &&
     $('input[name="cookies"]:checked').val() === 'option2' &&
     $('input[name="sleep"]:checked').val() === 'option1' &&
     $('input[name="shower"]:checked').val() === 'option2' &&
     $('input[name="cry"]:checked').val() === 'option2' &&
     $('input[name="years"]:checked').val() === 'option1'){
        window.location.href = 'market-place.html';
    } else{
        window.location.href = 'fail.html';
    }
} 

function initMap(){
    var options = {
        zoom: 6,
        center:{lat:22.9100,lng:-100.5528}
    }
    var map = new google.maps.Map(document.getElementById('map'), options);
    var marker = new google.maps.Marker({
        position: {lat:20.6597,lng:-103.3496},
        map: map
    });
    var marker1 = new google.maps.Marker({
        position: {lat:24.2669,lng:-98.8363},
        map: map
    });
    var marker2 = new google.maps.Marker({
        position: {lat:19.6058,lng:-99.0365},
        map: map
    });

    var infoWindow = new google.maps.InfoWindow({
        content: '<img src="assets/images/cartel-2.webp" alt="Chef #3">'
    })
    var infoWindow1 = new google.maps.InfoWindow({
        content: '<img src="assets/images/cartel-2.jpg" alt="Chef #2">'
    })
    var infoWindow2 = new google.maps.InfoWindow({
        content: '<img src="assets/images/en-17-07-18-CenAm-Gangs-Mexico-350x250-c-default.jpg" alt="Chef #1">'
    })

    marker.addListener('click', function(){
        infoWindow.open(map, marker);
    })
    marker1.addListener('click', function(){
        infoWindow1.open(map, marker1);
    })
    marker2.addListener('click', function(){
        infoWindow2.open(map, marker2);
    })
};