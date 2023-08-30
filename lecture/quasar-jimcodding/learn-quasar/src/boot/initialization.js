import { boot } from 'quasar/wrappers';
import { LocalStorage, Dark } from 'quasar';

// "async" is optional;
// more info on params: https://v2.quasar.dev/quasar-cli/boot-files
export default boot(async (/* { app, router, ... } */) => {
  console.log('### initialization ###');
  // dark modem 설정
  const darkMode = LocalStorage.getItem('darkMode');
  Dark.set(darkMode);
});
