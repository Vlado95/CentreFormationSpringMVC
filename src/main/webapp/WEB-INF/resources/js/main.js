$(function(){	
	$('[data-toggle="tooltip"]').tooltip();	
	$('[data-toggle="popover"]').popover();
	$('div#dialog').on('hidden.bs.modal', function (e) {
		$(this).removeData('bs.modal');
		$(this).find('.modal-content').html();
	});
	$('div#dialog').on('shown.bs.modal', function (e) {
		var url = $(e.relatedTarget).data('url');
		$(this).find('.modal-content').load(url);	
	});
});

