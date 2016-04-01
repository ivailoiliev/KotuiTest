$(document).ready(function(){
    function getPreviusUsername(){
        var userName = readCookie('username');
        if(userName!=null){
            $('#name').val(userName)
        }
    }

    function preparationGet(){
        $.get( "test.json", function( data ) {
            data = jQuery.parseJSON(data);
            allImages = data.data;
            imagesYouCanChooseFrom = allImages;
            chosenImages=[];
            shuffle(imagesYouCanChooseFrom);
            changeView('preparation');
            preparation();
            startTimer(60,'preparation');
        });
    }
    function preparation(){
        $( "#preparation .card .card-image" ).each(function( index ) {
            url = 'images/game/'+imagesYouCanChooseFrom[index].image;
          $( this ).css('background-image','url('+url+')')
        });
        $( "#preparation .card button" ).each(function( index ) {
          $( this ).attr('data-index',index);
        });
    }
    function chooseImage(index){
        chosenImages.push(imagesYouCanChooseFrom[index]);
        pushToStack(index);
        imagesYouCanChooseFrom.splice(index,1);
        if(chosenImages.length==3){
            waitForPreparation();
        }else{
            shuffle(imagesYouCanChooseFrom);
            preparation();
        }
    }
    function waitForPreparation(){
        stopTimer();
        changeView('waiting');
        setTimeout(function(){ startGame(); }, 3000);
    }
    function pushToStack(index){
        bottom = $('#stack div').length*15;
        card = '<div style="background-image:url(images/game/'+imagesYouCanChooseFrom[index].image+'); bottom:'+bottom+'px"></div>';
        $('#stack').append(card);
    }
    function startGame(){
        $.get( "test.json", function( data ) {
            data = jQuery.parseJSON(data);
            gameImages = data.data;
            $('#waiting').hide();
            $('#game').show();
            putCardsIntoContainer('#gameCardContainer',gameImages,false);
            startTimer(60,'game');
        });
    }
    function putCardsIntoContainer(container,images,answers){
        for(i=0;i<images.length;i++){
            var card = $("<div class='card'><div class='card-image'></div><input type='text'></div>");
            $('input ',card).attr('placeholder','What is this?')
            $('.card-image',card).css('background-image','url(images/game/'+images[i].image+')');
            if(answers==true) card.append(images[i].name);
            $(container).append(card);
        }
    }
    function gameOver(){
        changeView('waiting');
        stopTimer();
        $.get( "results.json", function( data ) {
            gameResults = jQuery.parseJSON(data);
            setTimeout(function(){ showResults(); }, 3000);
        });

    }
    function showResults(){
        changeView('results');
        $('#resultsMe .points').text(gameResults.me.points);
        if(gameResults.me.winner==true){
            $('#resultsMe .winner-label').text('Winner').addClass('green');
        }else{
            $('#resultsMe .winner-label').text('Loser').addClass('red');
        }

        $('#resultsYou .points').text(gameResults.you.points);
        if(gameResults.you.winner==true){
            $('#resultsYou .winner-label').text('Winner').addClass('green');
        }else{
            $('#resultsYou .winner-label').text('Loser').addClass('red');
        }
        putCardsIntoContainer('#resultsMe .wrong-cards-container',gameResults.me.wrong,true);
        putCardsIntoContainer('#resultsYou .wrong-cards-container',gameResults.you.wrong,true);

    }
    function startTimer(secs,task){
        timeLeft=secs
        timer = setInterval(function()
        {
          if (timeLeft != 0)
          {
            timeLeft--;
            $('#timer').text(timeLeft);
          }
          else
          {
              if(task=='preparation') waitForPreparation()
              if(task=='game') gameOver();
          }
        }, 1000);
    }
    function stopTimer(){
        $('#timer').text('');
        clearInterval(timer);
    }
    function changeView(view){
        $('#waiting,#preparation,#game,#login,#results').hide();
        $('#'+view).show();
    }

    //EVENTS

    $("#preparation .card button").click(function(){
        index  = $(this).attr('data-index');
        chooseImage(index);
    })
    $("#sendResults").click(function(){
        gameOver();
    })
    $('#startgame').click(function(){
        changeView('waiting');
        userNameInput = $('#name').val();
        createCookie('username',userNameInput,3);
        setTimeout(function(){ preparationGet(); }, 3000);
    });

    //INIT
    getPreviusUsername();
})


// functions

function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {

    // Pick a remaining element...
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    // And swap it with the current element.
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}

function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
