function fn() {   
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'train1'; // a custom 'intelligent' default
  }
    
  var config = { // base config JSON
    baseuri: 'https://jsonplaceholder.typicode.com'
  };
  
  if (env == 'train1') {
    // over-ride only those that need to be
    config.baseuri = 'https://jsonplaceholder.typicode.com';
  } else if (env == 'dev') {
    config.baseuri = 'https://jsonplaceholder.typicode.com';
  }
  
  // don't waste time waiting for a connection or if servers don't respond within 5 seconds
  karate.configure('connectTimeout', 10000);
  karate.configure('readTimeout', 5000);
  return config;
}