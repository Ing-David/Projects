// (function(){
  var config = {
    apiKey: "AIzaSyCeC7MjgPsyqEiMwNb3kLj_d9YKTkcW03g",
    authDomain: "job-search-i4gic.firebaseapp.com",
    databaseURL: "https://job-search-i4gic.firebaseio.com",
    projectId: "job-search-i4gic",
    storageBucket: "job-search-i4gic.appspot.com",
    messagingSenderId: "239085417537"
};
firebase.initializeApp(config);
document.getElementById('sign-in').addEventListener('submit',submitForm);
  
  
  function submitForm(e){
  e.preventDefault();
  var txtEmail= document.getElementById('txtEmail').value;
  var txtPass= document.getElementById('txtPass').value;
  a=txtEmail.length;
  b=txtPass.length;
  var database=firebase.database().ref('sign-up-account');
  database.on("value" ,snap => {
  for(var i=1 ; i <= (Object.keys(snap.val()).length); i++){
       if( (((snap.child('User'+i).child('Email').val()).length) != a) || (((snap.child('User'+i).child('Password').val()).length) != b) ){
          document.getElementById('demo').innerHTML="Your Email/Password does not match any account";
       }    
       if( ((snap.child('User'+i).child('Email').val()) == txtEmail) && (((snap.child('User'+i).child('Password').val()) == txtPass)) ){
        document.getElementById('demo').innerHTML="";
        document.getElementById('successfulsignin').innerHTML="You have logged in";
        setTimeout(function(){window.location.href='jobs-search-account.html'},1000);
          break;
      }
      
      else{
           document.getElementById('demo').innerHTML="Your Email/Password does not match any account";       
      }

    }
  });
  }

 





