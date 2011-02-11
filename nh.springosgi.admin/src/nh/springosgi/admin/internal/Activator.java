package nh.springosgi.admin.internal;

import org.eclipse.osgi.framework.console.CommandProvider;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.osgi.context.event.OsgiBundleApplicationContextListener;

public class Activator implements BundleActivator {

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
   */
  public void start(final BundleContext context) throws Exception {
    final SpringBundlesCommandProvider provider = new SpringBundlesCommandProvider();
    context.registerService(new String[] { OsgiBundleApplicationContextListener.class.getName(),
        CommandProvider.class.getName() }, provider, null);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
   */
  public void stop(final BundleContext context) throws Exception {
  }

  // class MyListener implements OsgiBundleApplicationContextListener {
  //
  // final CopyOnWriteArraySet<Bundle> _failedBundles = new CopyOnWriteArraySet<Bundle>();
  //
  // @Override
  // public void onOsgiApplicationEvent(OsgiBundleApplicationContextEvent event) {
  // if (event instanceof OsgiBundleContextFailedEvent) {
  // handleFailedContext((OsgiBundleContextFailedEvent) event);
  // }
  //
  // }
  //
  // protected void handleFailedContext(OsgiBundleContextFailedEvent event) {
  // _failedBundles.add(event.getBundle());
  // }
  // }

}
