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

        <q-toolbar-title> Quasar App </q-toolbar-title>

        <q-btn stretch flat label="Sub Layout" to="/sub" />
        <q-btn flat round dense :icon="darkModeIcon" @click="toggleDarkMode" />
        <!-- dark_mode -->
        <q-btn round size="sm" class="q-ml-md" to="/profile">
          <q-avatar>
            <img src="squirrel-logo.jpg" />
          </q-avatar>
        </q-btn>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script></script>

<script setup></script>
