import { boot } from 'quasar/wrappers';

export default boot(({ app }) => {
  app.config.globalProperties.hello = 'Hello Quasar!!';
});
