var config = {
    apiKey: "AIzaSyB9WLEsU-BTEAYlKg-CjEH4v8XUlz-2u7M",
    authDomain: "sign-up-5a23f.firebaseapp.com",
    databaseURL: "https://sign-up-5a23f.firebaseio.com",
    projectId: "sign-up-5a23f",
    storageBucket: "sign-up-5a23f.appspot.com",
    messagingSenderId: "952872734884"
  };
  firebase.initializeApp(config);
  document.getElementById('input_email').addEventListener('submit',submitForm);


  function sendverification(){
     var user=firebase.auth().currentUser;
     user.sendEmailVerification().then(function(){
           window.alert("Verification sent!");
     }).catch(function(error){
               window.alert("Error: " + error.message);
     });

  }

  function submitForm(e){
    e.preventDefault();
    var verifyemail= document.getElementById('verifyemail').value;
    a=verifyemail.length;
    var database=firebase.database().ref('sign-up-account');
    database.on("value" ,snap => {
    for(var i=1 ; i <= (Object.keys(snap.val()).length); i++){
         if( (((snap.child('User'+i).child('Email').val()).length) != a) || ((snap.child('User'+i).child('Password').val()) != verifyemail) ){
          sendverification();
            document.getElementById('demo').innerHTML="Your Email does not match any account!";
            break;
         }    
        
        else{
                 sendverification();
        }
  
      }
    });
    }
 

