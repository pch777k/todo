$(document).ready(function () {

	    $('.eBtn').on('click', function (event) {
	        event.preventDefault();
	        var href = $(this).attr('href');
	        
	            $.get(href, function (task) {
	                $('.updateForm #id').val(task.id);
	                $('.updateForm #description').val(task.description);
	                $('.updateForm #deadline').val(task.deadline);
	                $('.updateForm #priority').val(task.priority);
	                $('.updateForm #completed').val();
	                if(task.completed) {          	   
	                	$(".updateForm #completed").prop('checked', true);
	                } else {
	                	$(".updateForm #completed").prop('checked', false);
	                }
	            });
	            $('.updateForm #updateModal').modal();
	    });
	    
	    $('.delBtn').on('click', function(event) {
	        event.preventDefault();
	        var href = $(this).attr('href');
	        $('#removeModal #delRef').attr('href', href);
	        $('#removeModal').modal();
	    });
	    
});