function debounce(funct, wait, leading) {
    //common id between all the waiting methods
    var setTimeoutId;
    return function debounceExecuted() {
      // saving current scope and argument for funct function.
      var context = this;
      var args = arguments;
      //function to run after wait period
      var setDelayFunc = function() {
        //setting id to void before appyling function.
        setTimeoutId = null;
        //check if funct is already completed.
        if (!leading) funct.apply(context, args);
      };
      //check when to execute funct
      var immediateRun = leading && !setTimeoutId;
      //before running funct check if id is set,if so clear it out.
      clearTimeout(setTimeoutId);
      setTimeoutId = setTimeout(setDelayFunc, wait);
      if (immediateRun){
        funct.apply(context, args);console.log(args);
      }
    };
  };
  export default debounce;