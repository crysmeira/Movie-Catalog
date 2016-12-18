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