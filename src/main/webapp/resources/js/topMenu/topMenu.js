$(document).ready(function() {
    
    // Get the modal
    var projectsModal = document.getElementById('projectsModal');
    var manageModal = document.getElementById('manageModal');

    // Get the button that opens the modal
    var projectsBtn = document.getElementById('projectsBtn');
    var manageBtn = document.getElementById('infoBtn');
    
    // Get the <span> element that closes the modal
    var manageSpan = document.getElementsByClassName("close")[0];               
    // When the user clicks on the button, open the modal 
    projectsBtn.onclick = function() {
        projectsModal.style.display = "block";
    }
    manageBtn.onclick = function() {
        manageModal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    manageSpan.onclick = function() {
        manageModal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == manageModal) {
            manageModal.style.display = "none";
        }
        if (event.target == projectsModal) {
            projectsModal.style.display = "none";
        }
    }

    // active
    $('.activeBtn').click(function(e) {
        $('.activeBtn').css('color', 'blue');
    })
    
    $('html').click(function(e) {
        if( !$('.activeBtn').has(e.target).length) {
           $('.activeBtn').css('color', 'black');
        }
    });
    

    // activity 
    $(".activityBtn").click(function () {                $("#menu,.page_cover,html").addClass("open");
     window.location.hash = "#open"; 
    }); 

    window.onhashchange = function () { 
     if (location.hash != "#open") {  $("#menu,.page_cover,html").removeClass("open"); 
     }
    };
       
}); 