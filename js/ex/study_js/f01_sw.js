// import Unchained core and plugins.
importScripts(
  'node_modules/unchained-js/lib/core.js',
  'node_modules/unchained-js/lib/plugins/babel.js',
  'node_modules/unchained-js/lib/plugins/resolve.js'
);

// intercept fetch events.
this.addEventListener('fetch', (event) => {
  // verify if requested resource is an import.
  if (Unchained.check(event)) {
    event.respondWith(
        // resolve the resource response.
        Unchained.resolve(event)
    );
  }
});
