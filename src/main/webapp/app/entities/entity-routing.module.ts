import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'userprofile',
        data: { pageTitle: 'auditjHipsterApp.userprofile.home.title' },
        loadChildren: () => import('./userprofile/userprofile.module').then(m => m.UserprofileModule),
      },
      {
        path: 'region',
        data: { pageTitle: 'auditjHipsterApp.region.home.title' },
        loadChildren: () => import('./region/region.module').then(m => m.RegionModule),
      },
      {
        path: 'branch',
        data: { pageTitle: 'auditjHipsterApp.branch.home.title' },
        loadChildren: () => import('./branch/branch.module').then(m => m.BranchModule),
      },
      {
        path: 'department',
        data: { pageTitle: 'auditjHipsterApp.department.home.title' },
        loadChildren: () => import('./department/department.module').then(m => m.DepartmentModule),
      },
      {
        path: 'division',
        data: { pageTitle: 'auditjHipsterApp.division.home.title' },
        loadChildren: () => import('./division/division.module').then(m => m.DivisionModule),
      },
      {
        path: 'report',
        data: { pageTitle: 'auditjHipsterApp.report.home.title' },
        loadChildren: () => import('./report/report.module').then(m => m.ReportModule),
      },
      {
        path: 'fraud-investigation-report',
        data: { pageTitle: 'auditjHipsterApp.fraudInvestigationReport.home.title' },
        loadChildren: () =>
          import('./fraud-investigation-report/fraud-investigation-report.module').then(m => m.FraudInvestigationReportModule),
      },
      {
        path: 'fraud-knowledge-management',
        data: { pageTitle: 'auditjHipsterApp.fraudKnowledgeManagement.home.title' },
        loadChildren: () =>
          import('./fraud-knowledge-management/fraud-knowledge-management.module').then(m => m.FraudKnowledgeManagementModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
