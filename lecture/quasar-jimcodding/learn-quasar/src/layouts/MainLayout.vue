<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title> {{ $('productName') }} </q-toolbar-title>

        <q-btn stretch flat label="Sub Layout" to="/sub" />
        <q-btn flat round dense :icon="darkModeIcon" @click="toggleDarkMode" />
        <!-- dark_mode -->
        <q-btn round size="sm" class="q-ml-md">
          <q-avatar>
            <img src="squirrel-logo.jpg" />
          </q-avatar>
          <q-menu :offset="[0, 10]">
            <q-list style="min-width: 100px">
              <q-item clickable v-close-popup to="/profile">
                <q-item-section>프로필</q-item-section>
              </q-item>
              <q-separator />
              <q-item clickable v-close-popup to="auth/sign-in">
                <q-item-section>로그아웃</q-item-section>
              </q-item>
            </q-list>
          </q-menu>
        </q-btn>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list>
        <q-item-label header>
          <q-avatar class="q-mr-sm" size="lg">
            <img src="/squirrel-logo.jpg" />
          </q-avatar>
          <span>Quasar v{{ $q.version }}</span>
        </q-item-label>

        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
const linksList = [
  {
    title: 'Typography',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/typography',
  },
  {
    title: 'Colors',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/colors',
  },
  {
    title: 'Spacing',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/spacing',
  },
  {
    title: 'Breakpoints',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/breakpoints',
  },
  {
    title: 'Classes & Variables',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/classes-variables',
  },
  {
    title: 'flex Grid 1',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/flex-grid-1',
  },
  // {
  //   title: 'flex Grid 2',
  //   caption: 'quasar.dev',
  //   icon: 'school',
  //   to: '/flex-grid-2',
  // },
  {
    title: 'Form Handling',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/form-handling',
  },
  {
    title: 'Quasar Utils',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/quasar-utils',
  },
  {
    title: 'Quasar Language Packs',
    caption: 'quasar.dev',
    icon: 'school',
    to: '/quasar-language-packs',
  },
];
</script>

<script setup>
import { computed, ref } from 'vue';
import EssentialLink from 'components/EssentialLink.vue';
import { useQuasar } from 'quasar';

const $q = useQuasar();

const leftDrawerOpen = ref(false);
const essentialLinks = linksList;
const toggleLeftDrawer = () => (leftDrawerOpen.value = !leftDrawerOpen.value);
const darkModeIcon = computed(() =>
  $q.dark.isActive ? 'dark_mode' : 'light_mode',
);
// const init = () => {
//   const darkMode = $q.localStorage.getItem('darkMode');
//   console.log('darkMode: ', darkMode);
//   console.log('darkMode typeof: ', typeof darkMode);
//   $q.dark.set(darkMode);
// };
// init();
const toggleDarkMode = () => {
  $q.dark.toggle();
  $q.localStorage.set('darkMode', $q.dark.isActive);
};
</script>
