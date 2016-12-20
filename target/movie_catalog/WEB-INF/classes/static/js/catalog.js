$('#confirmDeletionModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  
  var idMovie = button.data('id');
  var titleMovie = button.data('title');
  
  var modal = $(this)
  var form = modal.find('form');
  var action = form.data('url-base');
  if (!action.endsWith('/')) {
	  action += '/';
  }
  
  form.attr('action', action + idMovie);
  modal.find('.modal-body span').html('Are you sure you want to delete the movie <strong>' + titleMovie + '</string>?');
});

// when the page is uploaded
$(function() {
	$('[rel="tooltip"]').tooltip();
	
	$('.js-update-status').on('click', function(event) {
		event.preventDefault(); // avoid the default behavior
		
		var buttonCheck = $(event.currentTarget);
		var urlCheck = buttonCheck.attr('href');
		
		// submit a PUT requisition in the url
		var response = $.ajax({
			url: urlCheck,
			type: 'PUT'
		});

		response.done(function(e) {
			var idMovie = buttonCheck.data('id');
			$('[data-role=' + idMovie + ']').html('<span class="label label-success">' + e + '</span>');
			buttonCheck.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Error: It was not possible to chenge the status');
		});
		
	});
});