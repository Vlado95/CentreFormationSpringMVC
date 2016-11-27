       
<div class="text-center">
    <h1><strong>BIENVENUE </strong></h1>

</div>
 <!--<h1>${titre}</h1>-->
<!--       
    <ul>
        <li><a href="projet-1">projet-1</a></li>
        <li><a href="projet-1-new-equipe">projet-1-new-equipe</a></li>
        <li><a href="projet">projets</a></li>
        <li><a href="new-projet">new-projet</a></li>
        <li><a href="projet-4-modifier">projet-4-modifier</a></li>
        <li><a href="equipe-1">equipe-1</a></li>
        <li><a href="equipe-7-new-membre">equipe-7-new-membre</a></li>
        <li><a href="personnes">Personnes</a></li
        <li><a href="personne-1">Personne-1</a></li>
        <li><a href="upload-1">upload-1</a></li>
        <li> <a href="equipe-1-sup-membre">equipe-1-new-membre</a></li>
        <li> <a href="download-document-1">download-1</a></li>
        <li> <a href="delete-document-2">delete</a></li>
         <li><a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="upload-2/1-modify">mod</a></li>
        
        
        
        
        
    </ul>-->





<!--
<div id="thanks"><p><a data-toggle="modal" href="#form-content" class="btn btn-primary">modal</a></p></div>
	 model content 
	<div id="form-content" class="modal hide fade in" style="display: none; "> 


	        <div class="modal-header">
	              <a class="close" data-dismiss="modal">×</a>
	              <h3>Contact us</h3>
	        </div>
		<div>
			<form class="contact">
			<fieldset>
		         <div class="modal-body">
		        	 <ul class="nav nav-list">
				<li class="nav-header">ID</li>
				<li><input class="input-xlarge" value=" <?=$this->id ?>" type="text" name="newId"></li>
				<li class="nav-header">NOM</li>
				<li><input class="input-xlarge" value="<?=$this->nom ?>" type="text" name="newNom"></li>
				<li class="nav-header">AGE</li>
				<li><input class="input-xlarge" value="<?=$this->age ?>" type="text" name="newAge"></li>
				<li class="nav-header">SEXE</li>
				<li><input class="input-xlarge" value="<?=$this->sexe ?>" type="text" name="newSexe"></li>
				</ul> 
		        </div>
			</fieldset>
			</form>
		</div>
	     <div class="modal-footer">
	         <button class="btn btn-success" id="submit">submit</button>
	         <a href="#" class="btn" data-dismiss="modal">Close</a>
  		</div>
	 </div> 


<script>
 $(function() {
//twitter bootstrap script
	$("button#submit").click(function(){
		   	$.ajax({
    		   	type: "POST",
			url: "<?=$this->url(array('controller'=>'index', 'action'=>'edit'/*, 'newId'=>$item->id, 'nom'=>$item->nom, 'age'=>$item->age, 'sexe'=>$item->genre*/))?>",
			data: $('form.contact').serialize(),
        		success: function(msg){
 	          	//	  $("#thanks").html(msg)
 		        	$("#form-content").modal('hide');	
 		        },
			error: function(){
				alert("failure");
				}
      			});
	});
});
</script>




-->
<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>


