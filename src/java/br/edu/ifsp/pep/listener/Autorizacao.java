package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.PessoaController;
import br.edu.ifsp.pep.modelo.Pessoa;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;

public class Autorizacao implements PhaseListener{

    @Inject
    private PessoaController pessoaController;
    
    @Override
    public void afterPhase(PhaseEvent event) {
         System.out.println("After: " + event.getPhaseId());
        
        if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES) {
            System.out.println("Após a fase Apply Request Values.");
        }

        FacesContext ctx = event.getFacesContext();

        String pagina = ctx.getViewRoot().getViewId();
        System.out.println(pagina);
        Pessoa pessoaAutenticada = pessoaController.getPessoaAutenticada();
        if ((pessoaAutenticada == null
                || !pessoaAutenticada.getTipo().equals("1"))
                && pagina.startsWith("/gerenciamento/")) {
            System.out.println("Redirecionando.....");
            
            NavigationHandler nh = ctx.getApplication()
                    .getNavigationHandler();

            nh.handleNavigation(ctx, null, "erro");
    }  
}

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("Before: " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}