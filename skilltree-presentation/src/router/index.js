import { createRouter, createWebHistory } from 'vue-router';
import home from './home';
import admin from './admin';
import trainer from './trainer';
import guard from './guard';

const delay = (t) => new Promise((r) => setTimeout(r, t)); // FF issue

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: home.concat(admin).concat(trainer),
  async scrollBehavior(to, from, savedPosition) {
    await delay(0); // FF issue
    return { top: 0 };
  }
});

guard(router, 'home');

export default router;
