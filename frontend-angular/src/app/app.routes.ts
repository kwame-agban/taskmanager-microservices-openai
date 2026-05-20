import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Tasks } from './pages/tasks/tasks';
import { AiAssistantComponent } from './ai-assistant/ai-assistant';
import { authGuard } from './core/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'tasks', component: Tasks, canActivate: [authGuard] },
  { path: 'ai-assistant', component: AiAssistantComponent, canActivate: [authGuard] },
  { path: '**', redirectTo: 'login' }
];
