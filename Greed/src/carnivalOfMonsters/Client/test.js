var screecher = '{"name":"Screecher","type":"Monster","landType":"DREAMLANDS","level":3,"dangerLevel":0,"monstrousLore":0,"victoryPoints":4}'
var aerie = '{"name":"AERIE Level 1","type":"Monster","landType":"AERIE","level":2,"dangerLevel":0,"monstrousLore":0,"victoryPoints":6}'
var caves = '{"name":"CAVES Level 1","type":"Monster","landType":"CAVES","level":2,"dangerLevel":0,"monstrousLore":0,"victoryPoints":6}'
var darklands = '{"name":"DARKLANDS Level 1","type":"Monster","landType":"DARKLANDS","level":2,"dangerLevel":0,"monstrousLore":0,"victoryPoints":6}'
var depths = '{"name":"DEPTHS Level 1","type":"Monster","landType":"DEPTHS","level":2,"dangerLevel":0,"monstrousLore":0,"victoryPoints":6}'
var enchantedForest = '{"name":"ENCHANTEDFOREST Level 1","type":"Monster","landType":"ENCHANTEDFOREST","level":2,"dangerLevel":0,"monstrousLore":0,"victoryPoints":6}'

var aerieland = '{"name":"AERIE","type":"Land","landType":"AERIE","landpoints":1}'
var cavesland = '{"name":"CAVES","type":"Land","landType":"CAVES","landpoints":1}'
var darklandsland = '{"name":"DARKLANDS","type":"Land","landType":"DARKLANDS","landpoints":1}'
var depthsland = '{"name":"DEPTHS","type":"Land","landType":"DEPTHS","landpoints":1}'
var enchantedforestland = '{"name":"ENCHANTEDFOREST","type":"Land","landType":"ENCHANTEDFOREST","landpoints":1}'
var dreamlandsland = '{"name":"DREAMLANDS","type":"Land","landType":"DREAMLANDS","landpoints":3}'

function test(){
    var test = document.getElementById('test').innerHTML;
    test += drawCard(JSON.parse(screecher));
    test += drawCard(JSON.parse(aerie));
    test += drawCard(JSON.parse(caves));
    test += drawCard(JSON.parse(darklands));
    test += drawCard(JSON.parse(depths));
    test += drawCard(JSON.parse(enchantedForest));
    test += '<br \>';
    test += drawCard(JSON.parse(dreamlandsland));
    test += drawCard(JSON.parse(aerieland));
    test += drawCard(JSON.parse(cavesland));
    test += drawCard(JSON.parse(darklandsland));
    test += drawCard(JSON.parse(depthsland));
    test += drawCard(JSON.parse(enchantedforestland));
    document.getElementById('test').innerHTML = test;

    $('[data-toggle="tooltip"]').tooltip();
}