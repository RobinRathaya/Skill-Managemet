<%@ include file="header.jsp"%>
<div class="content-wrapper">
	<section class="content-header">
		<h1>
			Add Topic <small>Create New Topic</small>
		</h1>
	</section>

	<!-- Main content -->
	<section class="content container-fluid">
		<div class="container">
			<div class="row">
				<div class="accordion" id="accordionExample" class="collap"></div>
			</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
</div>

<script src="js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('.collapse').collapse()
    $.ajax({url: "ViewDetails", success: function(res){
       var result = JSON.parse(res);
       var topics = result.map(r => r.topic.name);
       var uniqueTopics = [];
        $.each(topics, function(i, el){
            if($.inArray(el, uniqueTopics) === -1) uniqueTopics.push(el);
        });
		var questions = new Object();
        	uniqueTopics.forEach(u => {
        		questions[u] = result.filter(r => r.topic.name === u)
        	});
        uniqueTopics.forEach((t,i) => {
           	$("#accordionExample").append("<div class='card " + t + "'><div class='card-header' id='heading"+i+"'><h2 class='mb-0'><button class='btn btn-link collapsed' type='button' data-toggle='collapse' data-target='#collapse"+i+"' aria-expanded='false' aria-controls='collapse"+i+"'>" + t + "</button></h2></div><div>");
           	$("." + t).append("<div id='collapse"+i+"' class='collapse' aria-labelledby='heading"+i+"' data-parent='#accordionExample'><div class='card-body "+t+"a'></div></div>");
        });
        for (var key in questions) {
        	questions[key].forEach((q,i) => {
        		var answer = q.answers;
        		var index = i+1;
        		var options = q.options.split(/\s*,\s*/);
        		var className = key+i;
        		$("."+key+"a").append("<span class='abc"+className+"'>" + index + "). " + q.question + "</span><br><br><hr>")
        		//$("."+key).append("<span class=" + className + ">" + index + ".  " + q.question + "</span><br><br><br>");
        		options.forEach((o, j) => {
        			var index ="*";
        			$(".abc"+className).append("<br>" + index + " " + o + "");	
        		});
        		$(".abc"+className).append("<br>Answer: " + answer + "");
        	})
        }
    }});
  });
</script>
</body>
<%@ include file="footer.jsp"%>
