/*
 * Copyright © 2014 - 2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.model.cmmn.impl.instance;

import static org.camunda.bpm.model.cmmn.impl.CmmnModelConstants.CMMN11_NS;
import static org.camunda.bpm.model.cmmn.impl.CmmnModelConstants.CMMN_ATTRIBUTE_IMPORT_TYPE;
import static org.camunda.bpm.model.cmmn.impl.CmmnModelConstants.CMMN_ATTRIBUTE_LOCATION;
import static org.camunda.bpm.model.cmmn.impl.CmmnModelConstants.CMMN_ATTRIBUTE_NAMESPACE;
import static org.camunda.bpm.model.cmmn.impl.CmmnModelConstants.CMMN_ELEMENT_IMPORT;

import org.camunda.bpm.model.cmmn.instance.Import;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;
import org.camunda.bpm.model.xml.type.attribute.Attribute;

/**
 * @author Roman Smirnov
 *
 */
public class ImportImpl extends CmmnModelElementInstanceImpl implements Import {

  protected static Attribute<String> locationAttribute;
  protected static Attribute<String> namespaceAttribute;
  protected static Attribute<String> importTypeAttribute;

  public ImportImpl(ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public String getNamespace() {
    return namespaceAttribute.getValue(this);
  }

  public void setNamespace(String namespace) {
    namespaceAttribute.setValue(this, namespace);
  }

  public String getLocation() {
    return locationAttribute.getValue(this);
  }

  public void setLocation(String location) {
    locationAttribute.setValue(this, location);
  }

  public String getImportType() {
    return importTypeAttribute.getValue(this);
  }

  public void setImportType(String importType) {
    importTypeAttribute.setValue(this, importType);
  }

  public static void registerType(ModelBuilder modelBuilder) {
    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(Import.class, CMMN_ELEMENT_IMPORT)
      .namespaceUri(CMMN11_NS)
      .instanceProvider(new ModelTypeInstanceProvider<Import>() {
        public Import newInstance(ModelTypeInstanceContext instanceContext) {
          return new ImportImpl(instanceContext);
        }
      });

    namespaceAttribute = typeBuilder.stringAttribute(CMMN_ATTRIBUTE_NAMESPACE)
      .build();

    locationAttribute = typeBuilder.stringAttribute(CMMN_ATTRIBUTE_LOCATION)
      .required()
      .build();

    importTypeAttribute = typeBuilder.stringAttribute(CMMN_ATTRIBUTE_IMPORT_TYPE)
      .required()
      .build();

    typeBuilder.build();
  }

}
