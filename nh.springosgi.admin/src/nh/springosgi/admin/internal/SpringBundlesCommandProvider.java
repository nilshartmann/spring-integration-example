package nh.springosgi.admin.internal;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.Bundle;
import org.springframework.osgi.context.event.OsgiBundleApplicationContextEvent;
import org.springframework.osgi.context.event.OsgiBundleApplicationContextListener;
import org.springframework.osgi.context.event.OsgiBundleContextClosedEvent;
import org.springframework.osgi.context.event.OsgiBundleContextFailedEvent;
import org.springframework.osgi.context.event.OsgiBundleContextRefreshedEvent;

public class SpringBundlesCommandProvider implements CommandProvider, OsgiBundleApplicationContextListener <OsgiBundleApplicationContextEvent>{

  private final ConcurrentHashMap<Long, SpringContext> _contextEvents = new ConcurrentHashMap<Long, SpringContext>();

  /**
   * @param activator
   */
  SpringBundlesCommandProvider() {
  }

  @Override
  public String getHelp() {
    return "\nlc  <state>   List contexts. If state is not given, all contexts will be listed"
        +  "\nctx bundleId  Display information about application context in specified bundle";
  }

  /**
   * list contexts
   * 
   * @param ci
   */
  public void _lc(final CommandInterpreter ci) {

    final String expectedState = ci.nextArgument();

    for (final SpringContext event : _contextEvents.values()) {
      final Bundle bundle = event.getEvent().getBundle();

      final String stateLabel = event.getState().name();

      if (expectedState == null || stateLabel.startsWith(expectedState)) {
        final String contextState = String.format("%1$-5d %2$-12S %3$s_%4$s", bundle.getBundleId(), stateLabel, bundle
            .getSymbolicName(), bundle.getVersion());

        ci.println(contextState);

        if (event.getState() == SpringContextState.failed) {
          ci.println(String.format("%1$-18s %2$s", "", event.getFailedEvent().getFailureCause()));
        }
      }
      // System.out.println(bundle.getSymbolicName() + " -> Context " + event.getState().getLabel());
    }
  }

  public void _ctx(final CommandInterpreter commandInterpreter) {
    final String argument = commandInterpreter.nextArgument();
    if (argument == null) {
      commandInterpreter.println("Missing argument: bundle id");
    }
    final long bundleId = Long.valueOf(argument);
    final SpringContext springContext = _contextEvents.get(bundleId);
    if (springContext == null) {
      commandInterpreter.println(String.format("Bundle '%d' doesn't have an application context", bundleId));
      return;
    }

    final Bundle bundle = springContext.getEvent().getBundle();

    final String contextState = String.format("%1$-5d %2$-12S %3$s_%4$s", bundle.getBundleId(), springContext
        .getState(), bundle.getSymbolicName(), bundle.getVersion());

    commandInterpreter.println(contextState);
    if (springContext.getState() == SpringContextState.failed) {
      commandInterpreter.println("Failure cause: ");
      commandInterpreter.println(toString(springContext.getFailedEvent().getFailureCause()));
    }

  }

  protected String toString(final Throwable t) {
    try {
      final StringWriter writer = new StringWriter();
      t.printStackTrace(new PrintWriter(writer));
      return writer.toString();
    } catch (final Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void onOsgiApplicationEvent(final OsgiBundleApplicationContextEvent event) {
    SpringContextState state = null;

    if (event instanceof OsgiBundleContextRefreshedEvent) {
      state = SpringContextState.refreshed;
    } else if (event instanceof OsgiBundleContextFailedEvent) {
      state = SpringContextState.failed;
    } else if (event instanceof OsgiBundleContextClosedEvent) {
      state = SpringContextState.closed;
    }

    if (state == null) {
      return;
    }

    final SpringContext context = new SpringContext(state, event);
    _contextEvents.put(event.getBundle().getBundleId(), context);

  }

  enum SpringContextState {
    refreshed, closed, failed;
  }

  class SpringContext {
    private final SpringContextState                _state;

    private final OsgiBundleApplicationContextEvent _event;

    public SpringContext(final SpringContextState state, final OsgiBundleApplicationContextEvent event) {
      super();
      _state = state;
      _event = event;
    }

    public SpringContextState getState() {
      return _state;
    }

    public OsgiBundleApplicationContextEvent getEvent() {
      return _event;
    }

    public OsgiBundleContextFailedEvent getFailedEvent() {
      assert _event instanceof OsgiBundleContextFailedEvent;
      return (OsgiBundleContextFailedEvent) _event;
    }

  }

  public static void main(final String[] args) {
    try {

      System.out.println(String.format("%1$-5d %2$-9S %3$s_%4$s", 123L, "failed", "nh.test", "1.2.3.qualifier"));
      System.out.println(String.format("%1$-4S!", "a"));
      System.out.println(String.format("%1$-4S!", "AAAAA"));

    } catch (final Exception ex) {
      System.err.println("Exception caught in main: " + ex);
      ex.printStackTrace();
    }
  }

}