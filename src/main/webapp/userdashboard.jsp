<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/analytics.css" />
        <link
            rel="stylesheet"
            href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
            integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay"
            crossorigin="anonymous"
        />
        <title>User Dashboard</title>
    </head>
    <body>
        <nav class="nav-bar">
            <div class="left-container">
                <a href="#" class="link-nav active"
                    ><i class="fas fa-question-circle icon"></i> Exams</a
                >
                <a href="analytics.html" class="link-nav"
                    ><q class="fas fa-chart-bar icon"></q> Analytics</a
                >
            </div>
            <div class="right-container">
                <a href="#" class="link-nav-right">Student Name</a>
                <a href="#" class="link-nav-right"
                    ><i class="fas fa-cogs icon-right"></i
                ></a>
                <a href="#" class="link-nav-right icon-right"
                    ><i class="fas fa-sign-out-alt"></i
                ></a>
            </div>
        </nav>
        <div class="container table-container">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Available Exams</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="tableBody">
                        </tbody>
                    </table>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script>
    $(document).ready(function(){
    	$.post('TestManagement',{request:'UpcomingTest'},function(data){
    		var upcomingTest = JSON.parse(data);
    		upcomingTest.forEach(function(t, i) {
    			$("#tableBody").append('<tr><th scope="row">'+t.name+'</th><td><i class="fas fa-calendar-day inner-icon-div"></i> '+t.expiredDate.day+'/'+t.expiredDate.month+'/'+t.expiredDate.year+'</td><td><div class="divcont"><div><i class="fas fa-clipboard-list inner-icon-div"></i>'+t.topics.name+'</div><div><i class="fas fa-question-circle inner-icon-div"></i>'+t.noOfQuestion+' Questions</div><div><i class="fas fa-clock inner-icon-div"></i> '+t.durationTime.hour+':'+t.durationTime.minute+':'+t.durationTime.second+'</div></div></td><td class="cont-div-cent"><button value='+t.id+' class="btn btn-success takeTest">Start</button></td></tr>');    			
    		});
    	});
    	
    	$(document).on('click','.takeTest',function(e){
    		var quizId=e.target.value;
    		window.location="starttest.jsp?quiz_id="+quizId;
    	});
    	
    })
    </script>
</html>
