
var map;
var userLocation;
var markerImage;

var markers = [];

var minZoom = 2 ;
var events = [];

$(document).ready(function initialize() {

	var defaultLatLng = new google.maps.LatLng(49.8287299, 24.0182391); // Add
																		// the
																		// coordinates

	markerImage = {
		url : 'images/blue_marker.png',
		scaledSize : new google.maps.Size(30, 30)
	};

	var mapOptions = {
		center : defaultLatLng,
		zoom : 11, // The initial zoom level when your map loads (0-20)
		minZoom : minZoom, // Minimum zoom level allowed (0-20)
		maxZoom : 18, // Maximum soom level allowed (0-20)
		zoomControl : false, // Set to true if using zoomControlOptions
								// below, or false to remove all zoom controls.
		mapTypeId : google.maps.MapTypeId.ROADMAP, // Set the type of Map
		scrollwheel : true, // Enable Mouse Scroll zooming

		// All of the below are set to true by default, so simply remove if set
		// to true:
		panControl : false, // Set to false to disable
		mapTypeControl : false, // Disable Map/Satellite switch
		scaleControl : false, // Set to false to hide scale
		streetViewControl : false, // Set to disable to hide street view
		overviewMapControl : false, // Set to false to remove overview control
		rotateControl : false
	// Set to false to disable rotate control
	};
	var mapDiv = document.getElementById('map-canvas');
	map = new google.maps.Map(mapDiv, mapOptions);
	loadElements();
});




function onFirstPosition(position) {
	setUserLocation(position.coords.latitude, position.coords.longitude);
	map.panTo(userLocation);
}



function setUserLocation(lat, lng) {
	userLocation = new google.maps.LatLng(lat, lng);
}












function loadElements() {
	$.ajax({
				url : 'eventsMap',
				type : 'get',
				dataType : 'json',
				complete : function(data) {
					for (var i = 0; i < data.responseJSON.length; i++) {
						events.push(data.responseJSON[i]);
						var pos = new google.maps.LatLng(
								data.responseJSON[i].latitude,
								data.responseJSON[i].longitude);

					
						var content = '<div id="iw-container">'
								+ '<div class="iw-title" id="title'+i+'">'
								+ data.responseJSON[i].name
								+ '</div>'
								+ '<div class="iw-content">'
								+ '<div class="iw-subTitle" style="margin-top:-0.5em">Description</div>'
								+ '<p id="description'+i+'">'
								+ data.responseJSON[i].description
								+ '</p>'
								+ '<div class="iw-subTitle">Adress</div>'
								+ '<p id="adress'+i+'">'
								+ data.responseJSON[i].adress
								+ '</p>'
								+  '</div>' 
								+ '<div class="iw-bottom-gradient"></div>'
								+ '</div>';
						var infoWindow = new google.maps.InfoWindow({
							content : content,
							maxWidth : 400,
						// disableAutoPan: false
						});
						addlisteners(infoWindow,pos,data.responseJSON[i].name)
						
					

					}

				}
			});
}

function addlisteners(infoWindow, pos,name){
	google.maps.event
	.addListener(
			infoWindow,
			'domready',
			function() {

				// Reference to the DIV that wraps
				// the bottom of infowindow
				var iwOuter = $('.gm-style-iw');

				/*
				 * Since this div is in a position
				 * prior to .gm-div style-iw. We use
				 * jQuery and create a iwBackground
				 * variable, and took advantage of
				 * the existing reference
				 * .gm-style-iw for the previous div
				 * with .prev().
				 */
				var iwBackground = iwOuter.prev();

				// Removes background shadow DIV
				iwBackground.children(
						':nth-child(2)').css({
					'display' : 'none'
				});

				// Removes white background DIV
				iwBackground.children(
						':nth-child(4)').css({
					'display' : 'none'
				});

				// Moves the infowindow 115px to the
				// right.
				iwOuter.parent().parent().css({
					left : '115px'
				});

				// Moves the shadow of the arrow
				// 76px to the left margin.
				iwBackground
						.children(':nth-child(1)')
						.attr(
								'style',
								function(i, s) {
									return s
											+ 'left: 76px !important;'
								});

				// Moves the arrow 76px to the left
				// margin.
				iwBackground
						.children(':nth-child(3)')
						.attr(
								'style',
								function(i, s) {
									return s
											+ 'left: 76px !important;'
								});

				// Changes the desired tail shadow
				// color.
				iwBackground
						.children(':nth-child(3)')
						.find('div')
						.children()
						.css(
								{
									'box-shadow' : 'rgba(72, 181, 233, 0.6) 0px 1px 6px',
									'z-index' : '1'
								});

				// Reference to the div that groups
				// the close button elements.
				var iwCloseBtn = iwOuter.next();

				// Apply the desired effect to the
				// close button
				iwCloseBtn
						.css({
							opacity : '1',
							right : '38px',
							top : '3px',
							border : '7px solid #48b5e9',
							'border-radius' : '13px',
							'box-shadow' : '0 0 5px #3990B9',
							'box-sizing' : 'initial'
						});

				// If the content of infowindow not
				// exceed the set maximum height,
				// then the gradient is removed.
				if ($('.iw-content').height() < 140) {
					$('.iw-bottom-gradient').css({
						display : 'none'
					});
				}

				// The API automatically applies 0.7
				// opacity to the button after the
				// mouseout event. This function
				// reverses this event to the
				// desired value.
				iwCloseBtn.mouseout(function() {
					$(this).css({
						opacity : '1'
					});
				});
			});
var marker = new google.maps.Marker({
position : pos,
map : map,
draggable : false,
icon : markerImage,
title : name
});
google.maps.event.addListener(marker, 'click',
	function() {
		infoWindow.open(map, marker);
	});

// Event that closes the Info Window with a click on the
// map
google.maps.event.addListener(map, 'click', function() {
infoWindow.close();
});
markers.push(marker);
}
