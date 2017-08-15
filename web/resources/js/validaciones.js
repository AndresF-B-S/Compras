/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
  $('.true').mask('ZZZZZZZZZZ', {
    translation: {
      'Z': {
        pattern: /[a-zA-Z]/, optional: true
      }
    }
  });
 });